package com.diegobrsantosdev.order_system_api.config;

import com.diegobrsantosdev.order_system_api.entities.Category;
import com.diegobrsantosdev.order_system_api.entities.Order;
import com.diegobrsantosdev.order_system_api.entities.User;
import com.diegobrsantosdev.order_system_api.enums.OrderStatus;
import com.diegobrsantosdev.order_system_api.repositories.CategoryRepository;
import com.diegobrsantosdev.order_system_api.repositories.OrderRepository;
import com.diegobrsantosdev.order_system_api.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;

import java.util.Arrays;

@Configuration
@Profile("test")
@RequiredArgsConstructor
public class TestConfig implements CommandLineRunner {

    private final UserRepository userRepository;

    private final OrderRepository orderRepository;

    private final CategoryRepository categoryRepository;


    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Eletronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        User u1 = new User(null, "Diego Br Santos", "diegobrrsant@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Bruno A Pereira", "brunoapereira@gmail.com", "977777777", "123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

    }


}
