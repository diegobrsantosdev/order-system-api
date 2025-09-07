package com.diegobrsantosdev.order_system_api.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasswordDTO {

    private String oldPassword;
    private String newPassword;
}
