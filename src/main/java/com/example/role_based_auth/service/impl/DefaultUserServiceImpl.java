package com.example.role_based_auth.service.impl;

import com.example.role_based_auth.dto.UserRegisterDto;
import com.example.role_based_auth.entity.Role;
import com.example.role_based_auth.entity.UserEntity;
import com.example.role_based_auth.repository.RoleRepository;
import com.example.role_based_auth.repository.UserRepository;
import com.example.role_based_auth.service.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
public class DefaultUserServiceImpl implements DefaultUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserEntity save(UserRegisterDto userDto) {
        Role role = new Role();
        if(userDto.getRole().equals("USER"))
            role = roleRepository.findByRole("USER");
        else if(userDto.getRole().equals("ADMIN"))
            role = roleRepository.findByRole("ADMIN");

        UserEntity user = new UserEntity();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Set.of(role));

        return userRepository.save(user);
    }

    @Override
    public UserEntity getUser(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully!";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User '" + username + "' not found!");
        }

        return new User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).toList();
    }
}
