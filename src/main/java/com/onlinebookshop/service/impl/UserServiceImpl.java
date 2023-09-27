package com.onlinebookshop.service.impl;

import com.onlinebookshop.dto.user.UserRegistrationRequestDto;
import com.onlinebookshop.dto.user.UserRegistrationResponseDto;
import com.onlinebookshop.exception.RegistrationException;
import com.onlinebookshop.mapper.UserMapper;
import com.onlinebookshop.model.Role;
import com.onlinebookshop.model.ShoppingCart;
import com.onlinebookshop.model.User;
import com.onlinebookshop.repository.role.RoleRepository;
import com.onlinebookshop.repository.shoppingcart.ShoppingCartRepository;
import com.onlinebookshop.repository.user.UserRepository;
import com.onlinebookshop.service.ShoppingCartService;
import com.onlinebookshop.service.UserService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final ShoppingCartService shoppingCartService;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto request)
            throws RegistrationException {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RegistrationException("User with email: " + request.getEmail()
                    + "is already exists");
        }
        User user = setUserFromRequest(request);
        User savedUser = userRepository.save(user);
        shoppingCartService.createShoppingCart(savedUser);
        return userMapper.toResponseDto(savedUser);
    }

    private User setUserFromRequest(UserRegistrationRequestDto request) {
        User user = userMapper.toModel(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(roleRepository.getRoleByName(Role.RoleName.ROLE_USER)));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        return user;
    }
}
