package com.onlinebookshop.controller;

import com.onlinebookshop.dto.user.UserLoginRequestDto;
import com.onlinebookshop.dto.user.UserLoginResponseDto;
import com.onlinebookshop.dto.user.UserRegistrationRequestDto;
import com.onlinebookshop.dto.user.UserRegistrationResponseDto;
import com.onlinebookshop.exception.RegistrationException;
import com.onlinebookshop.security.AuthenticationService;
import com.onlinebookshop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public UserRegistrationResponseDto register(
            @RequestBody @Valid UserRegistrationRequestDto registrationRequest)
            throws RegistrationException {
        return userService.register(registrationRequest);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody UserLoginRequestDto loginRequestDto) {
        return authenticationService.authenticate(loginRequestDto);
    }

}
