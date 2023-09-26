package com.onlinebookshop.mapper;

import com.onlinebookshop.config.MapperConfig;
import com.onlinebookshop.dto.shoppingcart.ShoppingCartDto;
import com.onlinebookshop.model.ShoppingCart;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface ShoppingCartMapper {
    ShoppingCartDto toDto(ShoppingCart shoppingCart);

    ShoppingCart toModel(ShoppingCartDto shoppingCartDto);

    @AfterMapping
    default void setUserId(@MappingTarget ShoppingCartDto cartDto, ShoppingCart shoppingCart) {
        cartDto.setUserId(shoppingCart.getUser().getId());
    }
}
