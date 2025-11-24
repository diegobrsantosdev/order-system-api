package com.diegobrsantosdev.order_system_api.exceptions;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String email) {
        super("User not found with email: " + email);
    }
}
