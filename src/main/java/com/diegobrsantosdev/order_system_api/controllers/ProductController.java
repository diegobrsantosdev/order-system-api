package com.diegobrsantosdev.order_system_api.controllers;

import com.diegobrsantosdev.order_system_api.DTOs.ProductDTO;
import com.diegobrsantosdev.order_system_api.entities.Category;
import com.diegobrsantosdev.order_system_api.entities.Product;
import com.diegobrsantosdev.order_system_api.services.CategoryService;
import com.diegobrsantosdev.order_system_api.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> list = service.findAll()
                .stream()
                .map(ProductDTO::new)
                .toList();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        Product obj = service.findById(id);
        return ResponseEntity.ok().body(new ProductDTO(obj));
    }
}
