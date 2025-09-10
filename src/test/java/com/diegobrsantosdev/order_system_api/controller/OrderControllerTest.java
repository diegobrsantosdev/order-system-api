package com.diegobrsantosdev.order_system_api.controller;

import com.diegobrsantosdev.order_system_api.controllers.OrderController;
import com.diegobrsantosdev.order_system_api.entities.Order;
import com.diegobrsantosdev.order_system_api.entities.User;
import com.diegobrsantosdev.order_system_api.enums.OrderStatus;
import com.diegobrsantosdev.order_system_api.exceptions.ResourceNotFoundException;
import com.diegobrsantosdev.order_system_api.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrderService orderService;

    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        User client = new User();
        client.setId(1L);
        client.setName("Diego Teste");

        order = new Order(
                1L,
                Instant.now(),
                OrderStatus.WAITING_PAYMENT,
                client
        );
    }

    @Test
    void testFindAllOrders() throws Exception {
        when(orderService.findAll()).thenReturn(List.of(order));

        mockMvc.perform(get("/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(order.getId()))
                .andExpect(jsonPath("$[0].orderStatus").value(order.getOrderStatus().name()));
    }

    @Test
    void testFindByIdSuccess() throws Exception {
        when(orderService.findById(1L)).thenReturn(order);

        mockMvc.perform(get("/orders/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(order.getId()))
                .andExpect(jsonPath("$.orderStatus").value(order.getOrderStatus().name()));
    }

    @Test
    void testFindByIdNotFound() throws Exception {
        when(orderService.findById(2L)).thenThrow(new ResourceNotFoundException("Order not found. Id: 2"));
        mockMvc.perform(get("/orders/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}