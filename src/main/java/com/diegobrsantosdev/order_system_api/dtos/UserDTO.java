package com.diegobrsantosdev.order_system_api.dtos;

import com.diegobrsantosdev.order_system_api.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Pattern(regexp = "\\d{10,11}", message = "Phone must have 10 or 11 digits")
    @NotBlank(message = "Phone is mandatory")
    private String phone;

    @NotBlank(message = "Adress is mandatory")
    private String address;


    public UserDTO(User entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.phone = entity.getPhone();
        this.address = entity.getAddress();
    }

    public User toEntity() {
        User user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPhone(this.phone);
        user.setAddress(this.address);
        return user;
    }
}