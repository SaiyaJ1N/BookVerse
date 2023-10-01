package com.onlinebookshop.mapper;

import com.onlinebookshop.config.MapperConfig;
import com.onlinebookshop.dto.order.OrderItemResponseDto;
import com.onlinebookshop.model.CartItem;
import com.onlinebookshop.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(source = "book.id", target = "bookId")
    OrderItemResponseDto toResponseDto(OrderItem orderItem);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(source = "book.price", target = "price")
    OrderItem toOrderItem(CartItem cartItem);
}
