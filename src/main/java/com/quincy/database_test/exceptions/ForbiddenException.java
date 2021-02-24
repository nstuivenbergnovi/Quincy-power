package com.quincy.database_test.exceptions;

public class ForbiddenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ForbiddenException() {
        super("U kan deze handeling niet uitvoeren");
    }
}
