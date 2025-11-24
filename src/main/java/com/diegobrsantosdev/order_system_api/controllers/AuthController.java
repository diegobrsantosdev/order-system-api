package com.diegobrsantosdev.order_system_api.controllers;

import com.diegobrsantosdev.order_system_api.dtos.AuthResponseDTO;
import com.diegobrsantosdev.order_system_api.dtos.LoginRequestDTO;
import com.diegobrsantosdev.order_system_api.dtos.UserInsertDTO;
import com.diegobrsantosdev.order_system_api.security.JwtUtil;
import com.diegobrsantosdev.order_system_api.services.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        AuthResponseDTO response = authService.login(request);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody @Valid UserInsertDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.register(request));
    }

}