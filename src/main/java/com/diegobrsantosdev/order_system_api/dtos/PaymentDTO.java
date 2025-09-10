package com.diegobrsantosdev.order_system_api.dtos;

import com.diegobrsantosdev.order_system_api.entities.Payment;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
public class PaymentDTO {

    private Long id;
    private Instant moment;

    public PaymentDTO(Payment entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
    }
}
