package com.diegobrsantosdev.order_system_api.service;

import com.diegobrsantosdev.order_system_api.entities.User;
import com.diegobrsantosdev.order_system_api.exceptions.ResourceNotFoundException;
import com.diegobrsantosdev.order_system_api.repositories.UserRepository;
import com.diegobrsantosdev.order_system_api.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

;

public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService service;

    private User user;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(1L, "Diego Teste","testdiegod@gmail.com", "99999922","123456test");
    }

    //Find by id

    @Test
    void testFindById_Sucess() {
        when(repository.findById(1L)).thenReturn(Optional.of(user));
        User result = service.findById(1L);
        assertNotNull(result);
        assertEquals("Diego Teste", result.getName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound(){
        when(repository.findById(2L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(2L);
        } );
        assertEquals("User not found. Id: 2", exception.getMessage());
        verify(repository, times(1)).findById(2L);
    }

    //Insert user

    @Test
    void testInsert() {
        when(passwordEncoder.encode("123456test")).thenReturn("encodedPassword"); // Simula encode da senha
        when(repository.save(user)).thenReturn(user);
        user.setPassword("123456test"); // senha original
        User result = service.insert(user);
        assertNotNull(result);
        verify(passwordEncoder, times(1)).encode("123456test");
        verify(repository, times(1)).save(user);
    }


        //Delete user

    @Test
    void testDelete() {
        Long userId = 1L;
        when(repository.existsById(userId)).thenReturn(true);
        service.delete(userId);
        verify(repository, times(1)).deleteById(userId);
    }

    //Update user

    @Test
    void testUpdate() {
        User updatedUser = new User(null, "Diego Silva", "testdiegod@gmail.com", "99999922", "123456test");
        when(repository.findById(1L)).thenReturn(Optional.of(user));
        when(repository.save(any(User.class))).thenReturn(updatedUser);
        User result = service.update(1L, updatedUser);
        assertEquals("Diego Silva", result.getName());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void testUpdateNotFound(){
        when(repository.findById(2L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.update(2L, user);
        } );
        assertEquals("User not found. Id: 2", exception.getMessage());
        verify(repository, times(1)).findById(2L);
        verify(repository, times(0)).save(any(User.class));
    }

    //Update password

    @Test
    void testUpdatePassword() {
        String oldPassword = "oldpass";
        String newPassword = "newpass";
        user.setPassword("oldpass"); // senha atual do user
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(oldPassword, "oldpass")).thenReturn(true); // Simula a verificação da senha antiga
        when(passwordEncoder.encode(newPassword)).thenReturn("encodedNewPass");  // Nova senha codificada
        when(repository.save(user)).thenReturn(user);

        service.updatePassword(user.getId(), oldPassword, newPassword);

        assertEquals("encodedNewPass", user.getPassword());
        verify(passwordEncoder, times(1)).matches(oldPassword, "oldpass");
        verify(passwordEncoder, times(1)).encode(newPassword);
        verify(repository, times(1)).save(user);
    }

}