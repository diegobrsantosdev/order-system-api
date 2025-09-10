package com.diegobrsantosdev.order_system_api.dtos;

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
    private String orderStatus;
    private UserDTO client;
    private List<OrderItemDTO> items;
    private PaymentDTO payment;
    private Double total;

    public OrderDTO(Order entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
        this.orderStatus = entity.getOrderStatus().name();
        this.client = new UserDTO(entity.getClient());
        this.items = entity.getItems()
                .stream()
                .map(OrderItemDTO::new)
                .collect(Collectors.toList());
        this.total = entity.getTotal();

        if (entity.getPayment() != null) {
            this.payment = new PaymentDTO(entity.getPayment());
        }
    }
}
