package com.onlinebookshop.controller;

import com.onlinebookshop.dto.cartitem.CartItemRequestDto;
import com.onlinebookshop.dto.cartitem.CartItemResponseDto;
import com.onlinebookshop.dto.cartitem.CartItemUpdateRequestDto;
import com.onlinebookshop.dto.shoppingcart.ShoppingCartDto;
import com.onlinebookshop.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping cart management",
        description = "Endpoints for managing carts and carts items")
@RestController
@RequestMapping(value = "/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @Operation(summary = "Get shopping cart", description = "Get shopping cart with all cart items")
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ShoppingCartDto getShoppingCart() {
        return shoppingCartService.getShoppingCart();
    }

    @Operation(summary = "Add new cart item", description = "Add new cart item into shopping cart")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public CartItemResponseDto addCartItem(@RequestBody @Valid CartItemRequestDto cartItemDto) {
        return shoppingCartService.addCartItem(cartItemDto);
    }

    @Operation(summary = "Update cart item", description = "Update cart item in shopping cart")
    @PutMapping("/cart-items/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public CartItemResponseDto update(@PathVariable Long id,
                                      @RequestBody @Valid CartItemUpdateRequestDto updateDto) {
        return shoppingCartService.updateCartItem(id, updateDto);
    }

    @Operation(summary = "Delete cart item", description = "Delete cart item in shopping cart")
    @DeleteMapping("/cart-items/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public void delete(@PathVariable Long id) {
        shoppingCartService.deleteCartItem(id);
    }
}
