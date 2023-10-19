package com.onlinebookshop.service;

import com.onlinebookshop.dto.order.OrderItemResponseDto;
import com.onlinebookshop.dto.order.OrderRequestDto;
import com.onlinebookshop.dto.order.OrderResponseDto;
import com.onlinebookshop.dto.order.UpdateOrderStatusDto;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface OrderService {
    List<OrderResponseDto> getAll(Pageable pageable, Long userId);

    OrderResponseDto save(OrderRequestDto requestDto);

    List<OrderItemResponseDto> getAllOrderItemsByOrderId(Long id, Pageable pageable,
                                                         Long userId);

    OrderItemResponseDto getOrderItemFromOrder(Long orderId, Long itemId, Pageable pageable);

    OrderResponseDto updateOrderStatus(Long id, UpdateOrderStatusDto updateRequestDto);
}
