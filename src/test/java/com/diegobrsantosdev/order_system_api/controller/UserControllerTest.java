package com.diegobrsantosdev.order_system_api.controller;

import com.diegobrsantosdev.order_system_api.controllers.UserController;
import com.diegobrsantosdev.order_system_api.entities.User;
import com.diegobrsantosdev.order_system_api.services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Test
    void testFindById() throws Exception {
        User user = new User(1L, "Diego", "diego@email.com", "11999999999", "senha", "adress");
        Mockito.when(userService.findById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Diego"));
    }

    @Test
    void testInsert() throws Exception {
        User user = new User(2L, "Maria", "maria@email.com", "111111111", "senha", "address");
        Mockito.when(userService.insert(any(User.class))).thenReturn(user);

        String json = """
{
    "name":"Maria",
    "email":"maria@email.com",
    "phone":"111111111",
    "password":"senha"
    "address":"address"
}
""";
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Maria"));

    }
}
