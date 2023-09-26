package com.onlinebookshop.mapper;

import com.onlinebookshop.config.MapperConfig;
import com.onlinebookshop.dto.user.UserRegistrationRequestDto;
import com.onlinebookshop.dto.user.UserRegistrationResponseDto;
import com.onlinebookshop.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserRegistrationResponseDto toResponseDto(User user);

    User toModel(UserRegistrationRequestDto requestDto);
}
