package com.diegobrsantosdev.order_system_api.exceptions;

import java.io.Serial;

public class IncorrectPasswordException  extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public IncorrectPasswordException() {
        super("Incorrect password");
    }
}
