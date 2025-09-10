package com.diegobrsantosdev.order_system_api.controllers;
import com.diegobrsantosdev.order_system_api.dtos.PasswordDTO;
import com.diegobrsantosdev.order_system_api.dtos.UserDTO;
import com.diegobrsantosdev.order_system_api.dtos.UserInsertDTO;
import com.diegobrsantosdev.order_system_api.entities.User;
import com.diegobrsantosdev.order_system_api.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<UserDTO> list = service.findAll()
                .stream()
                .map(UserDTO::new)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        User user = service.findById(id);
        UserDTO dto = new UserDTO(user);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert(@RequestBody @Valid UserInsertDTO dto) {
        User entity = dto.toEntity();
        User saved = service.insert(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody @Valid UserDTO dto) {
        User user = dto.toEntity();
        User updated = service.update(id, user);
        UserDTO updatedDTO = new UserDTO(updated);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDTO);
    }


    @PutMapping("/{id}/password")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id, @RequestBody PasswordDTO passwordDTO) {
        service.updatePassword(id, passwordDTO.getOldPassword(), passwordDTO.getNewPassword());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
