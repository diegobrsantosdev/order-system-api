package com.diegobrsantosdev.order_system_api.services;

import com.diegobrsantosdev.order_system_api.DTOs.PasswordDTO;
import com.diegobrsantosdev.order_system_api.DTOs.UserDTO;
import com.diegobrsantosdev.order_system_api.entities.User;
import com.diegobrsantosdev.order_system_api.repositories.UserRepository;
import com.diegobrsantosdev.order_system_api.services.exceptions.ResourceNotFoundException;
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
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public User update(Long id, User obj) {
        User entity = repository.getReferenceById(id);
        updateData(entity,obj);
        return repository.save(entity);
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }

    public void updatePassword(Long id, String oldPassword, String newPassword) {
        User user = repository.getReferenceById(id);
        if (!user.getPassword().equals(oldPassword)) {
            throw new RuntimeException("Incorrect old password"); //provisório
        }
        user.setPassword(newPassword);
        repository.save(user);
    }

}
