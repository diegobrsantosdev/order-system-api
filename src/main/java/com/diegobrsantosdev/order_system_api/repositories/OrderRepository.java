package com.diegobrsantosdev.order_system_api.repositories;

import com.diegobrsantosdev.order_system_api.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
