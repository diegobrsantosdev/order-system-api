package com.diegobrsantosdev.order_system_api.service;

import com.diegobrsantosdev.order_system_api.entities.Order;
import com.diegobrsantosdev.order_system_api.exceptions.ResourceNotFoundException;
import com.diegobrsantosdev.order_system_api.repositories.OrderRepository;
import com.diegobrsantosdev.order_system_api.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService service;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        order = new Order();
        order.setId(1L);
        order.setMoment(Instant.now());
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(order));
        List<Order> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(order));
        Order result = service.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        Exception ex = assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(2L);
        });
        assertEquals("Order not found. Id: 2", ex.getMessage());
        verify(repository, times(1)).findById(2L);
    }
}
