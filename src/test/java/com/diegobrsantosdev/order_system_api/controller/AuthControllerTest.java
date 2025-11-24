package com.diegobrsantosdev.order_system_api.controller;

import com.diegobrsantosdev.order_system_api.controllers.AuthController;
import com.diegobrsantosdev.order_system_api.dtos.AuthResponseDTO;
import com.diegobrsantosdev.order_system_api.dtos.LoginRequestDTO;
import com.diegobrsantosdev.order_system_api.dtos.UserDTO;
import com.diegobrsantosdev.order_system_api.entities.User;
import com.diegobrsantosdev.order_system_api.exceptions.InvalidCredentialsException;
import com.diegobrsantosdev.order_system_api.security.JwtUtil;
import com.diegobrsantosdev.order_system_api.services.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Test
    void shouldLoginWithValidCredentials() throws Exception {
        User user = new User(
                1L,
                "Daniel Pereira Santos",
                "danielpereiras@gmail.com",
                "81986858382",
                "encodedPassword",
                "Rua das flores, 121, São Paulo, SP"
        );

        AuthResponseDTO response = new AuthResponseDTO(
                "FAKE_JWT_TOKEN",
                new UserDTO(user)
        );

        Mockito.when(authService.login(Mockito.any(LoginRequestDTO.class)))
                .thenReturn(response);

        String body = """
            {"email":"danielpereiras@gmail.com", "password":"123456"}
        """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("FAKE_JWT_TOKEN"))
                .andExpect(jsonPath("$.user.email").value("danielpereiras@gmail.com"));
    }

    @Test
    void shouldRejectInvalidPassword() throws Exception {

        Mockito.when(authService.login(Mockito.any(LoginRequestDTO.class)))
                .thenThrow(new InvalidCredentialsException("Invalid email or password."));

        String body = """
            {"email":"danielpereiras@gmail.com", "password":"wrongPass"}
        """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRejectNonexistentEmail() throws Exception {

        Mockito.when(authService.login(Mockito.any(LoginRequestDTO.class)))
                .thenThrow(new InvalidCredentialsException("Invalid email or password."));

        String body = """
            {"email":"notfound@email.com", "password":"whatever"}
        """;

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isUnauthorized());
    }
}