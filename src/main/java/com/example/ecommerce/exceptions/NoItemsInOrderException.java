package com.example.ecommerce.exceptions;

public class NoItemsInOrderException extends RuntimeException {

    public NoItemsInOrderException() {
        super();
    }

    public NoItemsInOrderException(String message, Throwable cause, boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);

    }

    public NoItemsInOrderException(String message, Throwable cause) {
        super(message, cause);

    }

    public NoItemsInOrderException(String message) {
        super(message);

    }

    public NoItemsInOrderException(Throwable cause) {
        super(cause);
    }
}
