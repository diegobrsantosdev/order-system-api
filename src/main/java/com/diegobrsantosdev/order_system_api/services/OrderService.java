package com.diegobrsantosdev.order_system_api.services;

import com.diegobrsantosdev.order_system_api.entities.Order;

import com.diegobrsantosdev.order_system_api.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class OrderService {

    private final OrderRepository repository;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id){
        Optional<Order> obj = repository.findById(id);
        return obj.orElse(null); //criar exceção personalizada depois.
    }
}
