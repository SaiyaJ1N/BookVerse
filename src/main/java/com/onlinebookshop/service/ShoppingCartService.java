package com.onlinebookshop.service;

import com.onlinebookshop.dto.cartitem.CartItemRequestDto;
import com.onlinebookshop.dto.cartitem.CartItemResponseDto;
import com.onlinebookshop.dto.cartitem.CartItemUpdateRequestDto;
import com.onlinebookshop.dto.shoppingcart.ShoppingCartDto;
import com.onlinebookshop.model.ShoppingCart;
import com.onlinebookshop.model.User;

public interface ShoppingCartService {
    ShoppingCartDto getShoppingCart();

    CartItemResponseDto addCartItem(CartItemRequestDto requestDto);

    CartItemResponseDto updateCartItem(Long id, CartItemUpdateRequestDto updateRequestDto);

    void deleteCartItem(Long id);

    void createShoppingCart(User user);
}
