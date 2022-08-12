package com.example.ecommerce.exceptions;

public class AlreadyInCartError extends RuntimeException{
    public AlreadyInCartError() {
        super();
    }
    public AlreadyInCartError(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

    public AlreadyInCartError(String message, Throwable cause) {
        super(message, cause);

    }

    public AlreadyInCartError(String message) {
        super(message);

    }

    public AlreadyInCartError(Throwable cause) {
        super(cause);
    }
}
