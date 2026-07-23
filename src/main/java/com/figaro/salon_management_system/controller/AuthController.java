package com.figaro.salon_management_system.controller;



import com.figaro.salon_management_system.dto.LoginRequest;
import com.figaro.salon_management_system.dto.LoginResponse;
import com.figaro.salon_management_system.dto.RegisterRequest;
import com.figaro.salon_management_system.model.User;
import com.figaro.salon_management_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }



    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}
