package com.figaro.salon_management_system.controller;

import com.figaro.salon_management_system.model.User;
import com.figaro.salon_management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public Map<String, Object> getProfile(Principal principal) {
        if (principal == null) {
            throw new RuntimeException("Unauthorized");
        }
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("fullName", user.getFullName());
        response.put("email", user.getEmail());
        response.put("role", user.getRole().name());
        return response;
    }

    @PutMapping("/profile")
    public Map<String, Object> updateProfile(Principal principal, @RequestBody Map<String, String> request) {
        if (principal == null) {
            throw new RuntimeException("Unauthorized");
        }
        User user = userRepository.findByEmail(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String newName = request.get("fullName");
        if (newName != null && !newName.trim().isEmpty()) {
            user.setFullName(newName.trim());
        }

        String newPassword = request.get("password");
        if (newPassword != null && !newPassword.trim().isEmpty()) {
            user.setPassword(passwordEncoder.encode(newPassword.trim()));
        }

        userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("id", user.getId());
        response.put("fullName", user.getFullName());
        response.put("email", user.getEmail());
        response.put("role", user.getRole().name());
        return response;
    }
}
