package com.onlinebookshop.service.impl;

import com.onlinebookshop.dto.order.OrderItemResponseDto;
import com.onlinebookshop.dto.order.OrderRequestDto;
import com.onlinebookshop.dto.order.OrderResponseDto;
import com.onlinebookshop.dto.order.UpdateOrderStatusDto;
import com.onlinebookshop.mapper.OrderItemMapper;
import com.onlinebookshop.mapper.OrderMapper;
import com.onlinebookshop.model.Order;
import com.onlinebookshop.model.OrderItem;
import com.onlinebookshop.model.ShoppingCart;
import com.onlinebookshop.model.User;
import com.onlinebookshop.repository.order.OrderItemRepository;
import com.onlinebookshop.repository.order.OrderRepository;
import com.onlinebookshop.security.AuthenticationService;
import com.onlinebookshop.service.OrderService;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final AuthenticationService authenticationService;

    @Override
    public List<OrderResponseDto> getAll(Pageable pageable) {
        return orderRepository.findOrderByUserId(authenticationService.getUserId()).stream()
                .map(orderMapper::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderResponseDto save(OrderRequestDto requestDto) {
        Order order = new Order();
        User user = authenticationService.getCurrentUser();
        ShoppingCart shoppingCart = authenticationService.getCartOfCurrentUser();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Order.Status.PENDING);
        order.setTotal(calculateOrderTotal(shoppingCart));
        order.setShippingAddress(requestDto.getShippingAddress());
        Order savedOrder = orderRepository.save(order);
        savedOrder.setOrderItems(shoppingCart.getCartItems().stream()
                .map(orderItemMapper::toOrderItem)
                .peek(orderItem -> orderItem.setOrder(savedOrder))
                .peek(this::saveOrderItemToDb)
                .collect(Collectors.toSet()));
        return orderMapper.toResponseDto(savedOrder);
    }

    @Override
    public List<OrderItemResponseDto> getAllOrderItemsByOrderId(Long id, Pageable pageable,
                                                                Authentication authentication) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can`t find order by id: " + id));
        User user = (User) authentication.getPrincipal();
        if (!order.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Current user does not have order by id: " + id);
        }
        return order.getOrderItems().stream()
                .map(orderItemMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemResponseDto getOrderItemFromOrder(Long orderId,
                                                      Long itemId, Pageable pageable) {
        Order order = orderRepository.findById(orderId).orElseThrow(() ->
                new NoSuchElementException("Can`t find order by id: " + orderId));
        return orderItemMapper.toResponseDto(order.getOrderItems().stream()
                .filter(orderItem -> orderItem.getId().equals(itemId))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can`t find order item by id:" + itemId)));
    }

    @Override
    public OrderResponseDto updateOrderStatus(Long id, UpdateOrderStatusDto updateRequest) {
        Order order = orderRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can`t find order by id: " + id));
        order.setStatus(updateRequest.getStatus());
        return orderMapper.toResponseDto(orderRepository.save(order));
    }

    private void saveOrderItemToDb(OrderItem orderItem) {
        orderItemRepository.save(orderItem);
    }

    private BigDecimal calculateOrderTotal(ShoppingCart shoppingCart) {
        return shoppingCart.getCartItems().stream()
                .map(cartItem -> cartItem.getBook()
                        .getPrice()
                        .multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .reduce(BigDecimal.valueOf(0), BigDecimal::add);
    }
}
