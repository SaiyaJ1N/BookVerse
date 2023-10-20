package com.onlinebookshop.mapper;

import com.onlinebookshop.config.MapperConfig;
import com.onlinebookshop.dto.cartitem.CartItemResponseDto;
import com.onlinebookshop.model.CartItem;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CartItemMapper {
    default CartItemResponseDto toResponseDto(CartItem cartItem) {
        CartItemResponseDto responseDto = new CartItemResponseDto();
        responseDto.setId(cartItem.getId());
        responseDto.setBookId(cartItem.getBook().getId());
        responseDto.setBookTitle(cartItem.getBook().getTitle());
        responseDto.setQuantity(cartItem.getQuantity());
        return responseDto;
    }
}
