package com.onlinebookshop.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Throwable exception) {
        super(message, exception);
    }
}
