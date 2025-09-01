package com.diegobrsantosdev.order_system_api.services;

import com.diegobrsantosdev.order_system_api.entities.User;
import com.diegobrsantosdev.order_system_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        Optional<User> obj = repository.findById(id);
        return obj.orElse(null); //criar exceção personalizada depois.
    }
}
