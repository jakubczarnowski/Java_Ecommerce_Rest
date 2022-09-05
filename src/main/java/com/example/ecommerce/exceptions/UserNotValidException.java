package com.example.ecommerce.exceptions;

public class UserNotValidException extends RuntimeException{
    public UserNotValidException() {
        super();
    }
    public UserNotValidException(String message, Throwable cause, boolean enableSuppression,
                             boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

    public UserNotValidException(String message, Throwable cause) {
        super(message, cause);

    }

    public UserNotValidException(String message) {
        super(message);

    }

    public UserNotValidException(Throwable cause) {
        super(cause);
    }
}
