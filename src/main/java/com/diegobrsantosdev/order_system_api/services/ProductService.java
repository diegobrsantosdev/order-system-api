package com.diegobrsantosdev.order_system_api.services;

import com.diegobrsantosdev.order_system_api.entities.Category;
import com.diegobrsantosdev.order_system_api.entities.Product;
import com.diegobrsantosdev.order_system_api.repositories.CategoryRepository;
import com.diegobrsantosdev.order_system_api.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Product findById(Long id){
        Optional<Product> obj = repository.findById(id);
        return obj.orElse(null); //criar exceção personalizada depois.
    }

}
