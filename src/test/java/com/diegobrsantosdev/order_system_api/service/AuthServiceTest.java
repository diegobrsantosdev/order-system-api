package com.diegobrsantosdev.order_system_api.service;

import com.diegobrsantosdev.order_system_api.dtos.AuthResponseDTO;
import com.diegobrsantosdev.order_system_api.dtos.LoginRequestDTO;
import com.diegobrsantosdev.order_system_api.dtos.UserInsertDTO;
import com.diegobrsantosdev.order_system_api.entities.User;
import com.diegobrsantosdev.order_system_api.exceptions.InvalidCredentialsException;
import com.diegobrsantosdev.order_system_api.security.JwtUtil;
import com.diegobrsantosdev.order_system_api.services.AuthService;
import com.diegobrsantosdev.order_system_api.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AuthServiceTest {
    private AuthService authService;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthService(userService, passwordEncoder, jwtUtil);
    }

    @Test
    void shouldLoginSuccessfully() {
        User user = new User(1L, "Daniel Pereira Santos", "danielpereiras@gmail.com", "81986858382", "encodedPassword", "Rua das flores, 121, São Paulo, SP");

        Mockito.when(userService.findByEmail("danielpereiras@gmail.com")).thenReturn(user);
        Mockito.when(passwordEncoder.matches("123456", "encodedPassword")).thenReturn(true);
        Mockito.when(jwtUtil.generateToken("danielpereiras@gmail.com")).thenReturn("FAKE_JWT_TOKEN");

        AuthResponseDTO response = authService.login(new LoginRequestDTO("danielpereiras@gmail.com", "123456"));

        assertEquals("FAKE_JWT_TOKEN", response.getToken());
        assertEquals("danielpereiras@gmail.com", response.getUser().getEmail());
    }

    @Test
    void shouldThrowExceptionOnInvalidPassword() {
        User user = new User(1L, "Daniel Pereira Santos", "danielpereiras@gmail.com", "81986858382", "encodedPassword", "Rua das flores, 121, São Paulo, SP");

        Mockito.when(userService.findByEmail("danielpereiras@gmail.com")).thenReturn(user);
        Mockito.when(passwordEncoder.matches("wrong", "encodedPassword")).thenReturn(false);

        assertThrows(InvalidCredentialsException.class,
                () -> authService.login(new LoginRequestDTO("danielpereiras@gmail.com", "wrong")));
    }

    @Test
    void shouldThrowExceptionOnNonexistentEmail() {
        Mockito.when(userService.findByEmail("notfound@email.com"))
                .thenThrow(new InvalidCredentialsException("notfound@email.com"));

        assertThrows(InvalidCredentialsException.class,
                () -> authService.login(new LoginRequestDTO("notfound@email.com", "whatever")));
    }

    @Test
    void shouldRegisterNewUserSuccessfully() {
        UserInsertDTO request = new UserInsertDTO();
        request.setName("Daniel Pereira Santos");
        request.setEmail("danielpereiras@gmail.com");
        request.setPhone("81986858382");
        request.setPassword("123456");
        request.setAddress("Rua das flores, 121, São Paulo, SP");


        User user = new User(null, request.getName(), request.getEmail(),
                request.getPhone(), "encodedPassword", request.getAddress());

        Mockito.when(userService.existsByEmail(request.getEmail())).thenReturn(false);
        Mockito.when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        Mockito.doAnswer(invocation -> {
            User u = invocation.getArgument(0);
            u.setId(1L);
            return u;
        }).when(userService).save(Mockito.any(User.class));
        Mockito.when(jwtUtil.generateToken(Mockito.anyString())).thenReturn("FAKE_JWT_TOKEN");

        AuthResponseDTO response = authService.register(request);

        assertEquals("FAKE_JWT_TOKEN", response.getToken());
        assertEquals("danielpereiras@gmail.com", response.getUser().getEmail());
        assertEquals("Daniel Pereira Santos", response.getUser().getName());
        assertEquals("Rua das Flores, 123", response.getUser().getAddress());
    }


}
