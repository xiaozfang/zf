package com.xiao.usercenter.exception;

public class CreateUserException extends RuntimeException {
    public CreateUserException() {
    }

    public CreateUserException(String message) {
        super(message);
    }
}
