package com.diegobrsantosdev.order_system_api.controller;

import com.diegobrsantosdev.order_system_api.controllers.AuthController;
import com.diegobrsantosdev.order_system_api.dtos.AuthResponseDTO;
import com.diegobrsantosdev.order_system_api.dtos.UserDTO;
import com.diegobrsantosdev.order_system_api.dtos.UserInsertDTO;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerRegisterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @MockitoBean
    private JwtUtil jwtUtil;

    @Test
    void shouldRegisterNewUserSuccessfully() throws Exception {
        User user = new User(
                1L,
                "Daniel Pereira Santos",
                "danielpereiras@gmail.com",
                "81986858382",
                "encodedPassword",
                "Rua das flores, 121,São Paulo, SP"
        );


        AuthResponseDTO response = new AuthResponseDTO(
                "FAKE_JWT_TOKEN",
                new UserDTO(user)
        );

        Mockito.when(authService.register(Mockito.any(UserInsertDTO.class)))
                .thenReturn(response);

        String body = """
            {"name":"Daniel Pereira Santos","email":"danielpereiras@gmail.com","phone":"81986858382","password":"123456", "address": "Rua das Flores, 121, São Paulo, SP"}
        """;

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").value("FAKE_JWT_TOKEN"))
                .andExpect(jsonPath("$.user.email").value("danielpereiras@gmail.com"))
                .andExpect(jsonPath("$.user.name").value("Daniel Pereira Santos"));
    }

    @Test
    void shouldRejectRegistrationWithExistingEmail() throws Exception {
        Mockito.when(authService.register(Mockito.any(UserInsertDTO.class)))
                .thenThrow(new InvalidCredentialsException("Email already in use."));

        String body = """
            {"name":"Daniel Pereira Santos","email":"danielpereiras@gmail.com","phone":"81986858382","password":"123456","address":"Rua das Flores, 121, São Paulo, SP"}
        """;

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isUnauthorized());
    }
}
