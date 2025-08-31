package com.diegobrsantosdev.order_system_api.resources;
import com.diegobrsantosdev.order_system_api.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResource {

    @GetMapping
    public ResponseEntity<User> findAll(){
        User u = new User(1L,"Diego","diegobrsantos@gmail.com","999999", "928428837");
        return ResponseEntity.ok().body(u);
    }

}
