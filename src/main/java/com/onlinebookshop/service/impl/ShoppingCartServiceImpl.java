package com.onlinebookshop.service.impl;

import com.onlinebookshop.dto.cartitem.CartItemRequestDto;
import com.onlinebookshop.dto.cartitem.CartItemResponseDto;
import com.onlinebookshop.dto.cartitem.CartItemUpdateRequestDto;
import com.onlinebookshop.dto.shoppingcart.ShoppingCartDto;
import com.onlinebookshop.exception.EntityNotFoundException;
import com.onlinebookshop.mapper.CartItemMapper;
import com.onlinebookshop.mapper.ShoppingCartMapper;
import com.onlinebookshop.model.CartItem;
import com.onlinebookshop.model.ShoppingCart;
import com.onlinebookshop.model.User;
import com.onlinebookshop.repository.cartitem.CartItemRepository;
import com.onlinebookshop.repository.shoppingcart.ShoppingCartRepository;
import com.onlinebookshop.security.AuthenticationService;
import com.onlinebookshop.service.BookService;
import com.onlinebookshop.service.ShoppingCartService;
import jakarta.transaction.Transactional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final CartItemMapper cartItemMapper;
    private final ShoppingCartMapper shoppingCartMapper;
    private final BookService bookService;
    private final CartItemRepository cartItemRepository;
    private final AuthenticationService authenticationService;

    @Override
    @Transactional
    public ShoppingCartDto getShoppingCart() {
        ShoppingCartDto shoppingCartDto = shoppingCartMapper.toDto(authenticationService.getCartOfCurrentUser());
        shoppingCartDto.setCartItems(authenticationService.getCartOfCurrentUser().getCartItems().stream()
                .map(cartItemMapper::toResponseDto)
                .collect(Collectors.toSet()));
        return shoppingCartDto;
    }

    @Override
    @Transactional
    public CartItemResponseDto addCartItem(CartItemRequestDto requestDto) {
        ShoppingCart cart = authenticationService.getCartOfCurrentUser();
        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(cart);
        cartItem.setBook(bookService.findBookById(requestDto.getBookId()));
        cartItem.setQuantity(requestDto.getQuantity());
        CartItemResponseDto cartItemDto = cartItemMapper
                .toResponseDto(cartItemRepository.save(cartItem));
        ShoppingCartDto shoppingCartDto = shoppingCartMapper.toDto(cart);
        shoppingCartDto.getCartItems().add(cartItemDto);
        return cartItemDto;
    }

    @Override
    @Transactional
    public CartItemResponseDto updateCartItem(Long id, CartItemUpdateRequestDto updateRequestDto) {
        CartItem cartItem = cartItemRepository
                .findCartItemByIdAndShoppingCartId(id, authenticationService.getCartOfCurrentUser().getId())
                .orElseThrow(() -> new EntityNotFoundException("Can't find item with id= " + id));
        cartItem.setQuantity(updateRequestDto.getQuantity());
        cartItem.setId(id);
        return cartItemMapper.toResponseDto(cartItemRepository.save(cartItem));
    }

    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public void createShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setId(user.getId());
        shoppingCart.setUser(user);
        shoppingCartRepository.save(shoppingCart);
    }
}
