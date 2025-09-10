package com.diegobrsantosdev.order_system_api.dtos;

import com.diegobrsantosdev.order_system_api.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInsertDTO {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is mandatory")
    private String email;

    private String phone;

    @NotBlank(message = "Password is mandatory")
    private String password;

    public User toEntity() {
        return new User(null, name, email, phone, password);
    }

}
