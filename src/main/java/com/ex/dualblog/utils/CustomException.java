package com.ex.dualblog.utils;

public class CustomException extends RuntimeException {

    private String errorMessage;

    public CustomException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}