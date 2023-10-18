package com.onlinebookshop.controller;

import com.onlinebookshop.dto.order.OrderItemResponseDto;
import com.onlinebookshop.dto.order.OrderRequestDto;
import com.onlinebookshop.dto.order.OrderResponseDto;
import com.onlinebookshop.dto.order.UpdateOrderStatusDto;
import com.onlinebookshop.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Orders controller", description = "Endpoints to managing orders")
@RequestMapping("/orders")
@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "Get all user`s orders",
            description = "Retrieve user's order history")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public List<OrderResponseDto> getAll(Pageable pageable) {
        return orderService.getAll(pageable);
    }

    @Operation(summary = "Create a new order",
            description = "Place a new user`s order")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public OrderResponseDto createOrder(@RequestBody @Valid OrderRequestDto requestDto) {
        return orderService.save(requestDto);
    }

    @Operation(summary = "Get all Order items",
            description = "Retrieve all OrderItems for a specific order")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{orderId}/items")
    public List<OrderItemResponseDto> getAllOrderItemsByOrderId(@PathVariable Long orderId,
                                                                Pageable pageable,
                                                                Authentication auth) {
        return orderService.getAllOrderItemsByOrderId(orderId, pageable, auth);
    }

    @Operation(summary = "Get order item from order",
            description = "Retrieve a specific OrderItem within an order")
    @GetMapping("/{orderId}/items/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public OrderItemResponseDto getOrderItemFromOrder(@PathVariable Long orderId,
                                                      @PathVariable Long id, Pageable pageable) {
        return orderService.getOrderItemFromOrder(orderId, id, pageable);
    }

    @Operation(summary = "Update Order status",
            description = "Update the status of an order")
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public OrderResponseDto updateOrderStatus(@PathVariable Long id,
                                              @RequestBody
                                              @Valid UpdateOrderStatusDto updateRequestDto) {
        return orderService.updateOrderStatus(id, updateRequestDto);
    }

}
