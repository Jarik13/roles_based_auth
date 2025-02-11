package com.example.role_based_auth.service;

import com.example.role_based_auth.dto.UserRegisterDto;
import com.example.role_based_auth.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface DefaultUserService extends UserDetailsService {
    UserEntity save(UserRegisterDto userDto);
    UserEntity getUserByEmail(String email);
    UserEntity getUserById(Long id);
    String deleteUser(Long id);
}
