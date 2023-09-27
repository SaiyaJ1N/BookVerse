package com.onlinebookshop.service;

import com.onlinebookshop.dto.user.UserRegistrationRequestDto;
import com.onlinebookshop.dto.user.UserRegistrationResponseDto;
import com.onlinebookshop.exception.RegistrationException;
import com.onlinebookshop.model.ShoppingCart;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto)
                        throws RegistrationException;
}
