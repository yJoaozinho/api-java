package com.joao.demoparkapi.exception;

public class UsernameUniqueViolationExeption extends RuntimeException {
    public UsernameUniqueViolationExeption(String message) {
        super(message);
    }
}
