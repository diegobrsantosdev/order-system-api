package com.diegobrsantosdev.order_system_api.controller;


import com.diegobrsantosdev.order_system_api.controllers.CategoryController;
import com.diegobrsantosdev.order_system_api.entities.Category;
import com.diegobrsantosdev.order_system_api.exceptions.ResourceNotFoundException;
import com.diegobrsantosdev.order_system_api.services.CategoryService;
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

@WebMvcTest(CategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CategoryService categoryService;

    @Test
    void testFindAllCategories() throws Exception {
        Category cat = new Category(1L, "TestCategory");
        Mockito.when(categoryService.findAll()).thenReturn(List.of(cat));
        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("TestCategory"));
    }

    @Test
    void testFindByIdSuccess() throws Exception {
        Category cat = new Category(1L, "TestCategory");
        Mockito.when(categoryService.findById(1L)).thenReturn(cat);
        mockMvc.perform(get("/categories/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("TestCategory"));
    }

    @Test
    void testFindByIdNotFound() throws Exception {
        Mockito.when(categoryService.findById(2L)).thenThrow(new ResourceNotFoundException("Category not found"));
        mockMvc.perform(get("/categories/2"))
                .andExpect(status().is4xxClientError());
    }
}

