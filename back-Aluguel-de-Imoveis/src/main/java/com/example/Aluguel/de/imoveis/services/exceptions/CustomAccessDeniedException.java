package com.example.Aluguel.de.imoveis.services.exceptions;

public class CustomAccessDeniedException extends RuntimeException {
    public CustomAccessDeniedException(String message) {
        super(message);
    }
}
