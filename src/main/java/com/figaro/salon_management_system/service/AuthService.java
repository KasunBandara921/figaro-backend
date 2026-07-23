package com.figaro.salon_management_system.service;



import com.figaro.salon_management_system.dto.RegisterRequest;
import com.figaro.salon_management_system.model.Role;
import com.figaro.salon_management_system.model.User;
import com.figaro.salon_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.figaro.salon_management_system.dto.LoginRequest;
import com.figaro.salon_management_system.dto.LoginResponse;
import com.figaro.salon_management_system.security.JwtUtil;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(RegisterRequest request) {

        // check if email already used
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // encrypted here
        user.setRole(Role.CUSTOMER); // every new signup is a CUSTOMER by default

        return userRepository.save(user);
    }


    @Autowired
    private JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
        return new LoginResponse(token, user.getRole().name(), user.getFullName());
    }
}
