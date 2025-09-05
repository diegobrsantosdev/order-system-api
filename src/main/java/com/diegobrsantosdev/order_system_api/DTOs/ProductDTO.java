package com.diegobrsantosdev.order_system_api.DTOs;

import com.diegobrsantosdev.order_system_api.entities.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;
    private Set<CategoryDTO> categories;

    public ProductDTO(Product entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.price = entity.getPrice();
        this.imgUrl = entity.getImgUrl();
        this.categories = entity.getCategories().stream()
                .map(CategoryDTO::new)
                .collect(Collectors.toSet());
    }


}