package com.onlinebookshop.mapper;

import com.onlinebookshop.config.MapperConfig;
import com.onlinebookshop.dto.order.OrderRequestDto;
import com.onlinebookshop.dto.order.OrderResponseDto;
import com.onlinebookshop.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, uses = OrderItemMapper.class)
public interface OrderMapper {
    Order toModel(OrderRequestDto requestDto);

    @Mapping(source = "user.id", target = "userId")
    OrderResponseDto toResponseDto(Order order);
}
