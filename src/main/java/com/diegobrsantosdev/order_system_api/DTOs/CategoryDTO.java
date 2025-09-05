package com.diegobrsantosdev.order_system_api.DTOs;

import com.diegobrsantosdev.order_system_api.entities.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String name;

    public CategoryDTO(Category entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

}