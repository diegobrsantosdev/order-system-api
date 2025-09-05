package com.diegobrsantosdev.order_system_api.DTOs;

import com.diegobrsantosdev.order_system_api.entities.Order;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class OrderDTO {

    private Long id;
    private Instant moment;
    private String orderStatus; // Enum convertido para String
    private UserDTO client;
    private List<OrderItemDTO> items;

    public OrderDTO(Order entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
        this.orderStatus = entity.getOrderStatus().name();
        this.client = new UserDTO(entity.getClient());
        this.items = entity.getItems().stream()
                .map(OrderItemDTO::new)
                .collect(Collectors.toList());
    }
}
