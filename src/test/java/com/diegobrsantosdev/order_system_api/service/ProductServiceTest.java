package com.diegobrsantosdev.order_system_api.service;

import com.diegobrsantosdev.order_system_api.entities.Product;
import com.diegobrsantosdev.order_system_api.exceptions.ResourceNotFoundException;
import com.diegobrsantosdev.order_system_api.repositories.ProductRepository;
import com.diegobrsantosdev.order_system_api.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductService service;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product(1L, "Notebook", "Notebook Gamer", 4500.0, null);
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(List.of(product));
        List<Product> result = service.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Notebook", result.get(0).getName());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testFindById_Success() {
        when(repository.findById(1L)).thenReturn(Optional.of(product));
        Product result = service.findById(1L);

        assertNotNull(result);
        assertEquals("Notebook", result.getName());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void testFindById_NotFound() {
        when(repository.findById(2L)).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            service.findById(2L);
        });
        assertEquals("Product not found. Id: 2", exception.getMessage());
        verify(repository, times(1)).findById(2L);

    }

}