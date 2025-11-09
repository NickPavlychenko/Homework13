package org.skypro.skyshop.exception;

public class BestResultNotFoundException extends Exception {
    public BestResultNotFoundException(String message) {
        super(message);
    }

    public BestResultNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}