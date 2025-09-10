package com.diegobrsantosdev.order_system_api.controller;

import com.diegobrsantosdev.order_system_api.controllers.ProductController;
import com.diegobrsantosdev.order_system_api.entities.Product;
import com.diegobrsantosdev.order_system_api.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void testFindAllProducts() throws Exception {
        Product prod = new Product(1L, "Produto Teste", "desc", 10.0, null);
        Mockito.when(productService.findAll()).thenReturn(List.of(prod));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Produto Teste"));

    }

    @Test
    void testFindById() throws Exception {
        Product prod = new Product(1L, "Produto Teste", "desc", 10.0, null);
        Mockito.when(productService.findById(1L)).thenReturn(prod);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Produto Teste"));
    }
}