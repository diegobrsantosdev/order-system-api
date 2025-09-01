package com.diegobrsantosdev.order_system_api.config;

import com.diegobrsantosdev.order_system_api.entities.User;
import com.diegobrsantosdev.order_system_api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Diego Br Santos", "diegobrrsant@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Bruno A Pereira", "brunoapereira@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
