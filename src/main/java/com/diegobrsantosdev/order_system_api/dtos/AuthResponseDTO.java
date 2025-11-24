package com.diegobrsantosdev.order_system_api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO implements Serializable {
    private String token;
    private UserDTO user;
}
