package com.diegobrsantosdev.order_system_api.services;
import com.diegobrsantosdev.order_system_api.entities.User;
import com.diegobrsantosdev.order_system_api.exceptions.IncorrectPasswordException;
import com.diegobrsantosdev.order_system_api.repositories.UserRepository;
import com.diegobrsantosdev.order_system_api.exceptions.DatabaseException;
import com.diegobrsantosdev.order_system_api.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
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
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj) {
        User entity = repository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(id));
        updateData(entity,obj);
        return repository.save(entity);
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }

    public void updatePassword(Long id, String oldPassword, String newPassword) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        if (!user.getPassword().equals(oldPassword)) {
            throw new IncorrectPasswordException();
        }
        user.setPassword(newPassword);
        repository.save(user);
    }

}
