package com.onlinebookshop.service;

import com.onlinebookshop.dto.user.UserRegistrationRequestDto;
import com.onlinebookshop.dto.user.UserRegistrationResponseDto;
import com.onlinebookshop.exception.RegistrationException;
import org.springframework.stereotype.Service;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto)
                        throws RegistrationException;
}
