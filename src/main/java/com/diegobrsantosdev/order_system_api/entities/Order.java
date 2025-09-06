package com.diegobrsantosdev.order_system_api.entities;

import com.diegobrsantosdev.order_system_api.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Table(name = "tb_orders")
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @OneToMany(mappedBy = "id.order")
    @Setter(AccessLevel.NONE)
    private Set<OrderItem> items = new HashSet<>();


    // Custom constructor accepting OrderStatus
    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus); // uses custom setter
        this.client = client;
    }

    // Custom Getter: convert Integer to Enum
    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    // Custom setter: receives Enum and saves Integer
    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode(); // uses getCode() of enum
        }
    }

    public Double getTotal(){
        double sum = 0;
        for (OrderItem orderItem : items) {
            sum += orderItem.getSubTotal();
        }
        return sum;
    }



}
