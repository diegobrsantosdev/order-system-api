package com.diegobrsantosdev.order_system_api.repositories;

import com.diegobrsantosdev.order_system_api.entities.Product;
import com.diegobrsantosdev.order_system_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
