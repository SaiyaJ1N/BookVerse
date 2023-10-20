package com.onlinebookshop.security;

import com.onlinebookshop.dto.user.UserLoginRequestDto;
import com.onlinebookshop.dto.user.UserLoginResponseDto;
import com.onlinebookshop.model.ShoppingCart;
import com.onlinebookshop.model.User;
import com.onlinebookshop.repository.shoppingcart.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final ShoppingCartRepository shoppingCartRepository;

    public UserLoginResponseDto authenticate(UserLoginRequestDto requestDto) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getEmail(),
                        requestDto.getPassword())
        );

        String token = jwtUtil.generateToken(requestDto.getEmail());
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto();
        userLoginResponseDto.setToken(token);
        return userLoginResponseDto;
    }

    public Long getUserId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

    public ShoppingCart getCartOfCurrentUser() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return shoppingCartRepository.findByUserId(user.getId());
    }

    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
