package com.diegobrsantosdev.order_system_api.services;

import com.diegobrsantosdev.order_system_api.dtos.AuthResponseDTO;
import com.diegobrsantosdev.order_system_api.dtos.LoginRequestDTO;
import com.diegobrsantosdev.order_system_api.dtos.UserDTO;
import com.diegobrsantosdev.order_system_api.dtos.UserInsertDTO;
import com.diegobrsantosdev.order_system_api.entities.User;
import com.diegobrsantosdev.order_system_api.exceptions.InvalidCredentialsException;
import com.diegobrsantosdev.order_system_api.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponseDTO login(LoginRequestDTO request) {
        User user = userService.findByEmail(request.getEmail());

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password.");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponseDTO(token, new UserDTO(user));
    }

    public AuthResponseDTO register(UserInsertDTO request) {
        if (userService.existsByEmail(request.getEmail())) {
            throw new InvalidCredentialsException("Email already in use.");
        }

        User user = request.toEntity();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userService.save(user);

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponseDTO(token, new UserDTO(user));
    }


}
