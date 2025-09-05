package com.diegobrsantosdev.order_system_api.DTOs;

import com.diegobrsantosdev.order_system_api.entities.OrderItem;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
    private Integer quantity;
    private Double price;
    private ProductDTO product;

    public OrderItemDTO(OrderItem entity) {
        this.quantity = entity.getQuantity();
        this.price = entity.getPrice();
        this.product = new ProductDTO(entity.getProduct());
    }
}
