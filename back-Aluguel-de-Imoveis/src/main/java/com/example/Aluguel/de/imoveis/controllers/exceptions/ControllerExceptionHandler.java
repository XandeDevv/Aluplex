package com.example.Aluguel.de.imoveis.controllers.exceptions;

import com.example.Aluguel.de.imoveis.services.exceptions.ControllerNotFoundException;
import com.example.Aluguel.de.imoveis.services.exceptions.CustomAccessDeniedException;
import com.example.Aluguel.de.imoveis.services.exceptions.DataBaseException;
import com.example.Aluguel.de.imoveis.services.exceptions.CustomUnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ControllerNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ControllerNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError erro= new StandardError();
        erro.setTimeStamp(Instant.now());
        erro.setMessage(e.getMessage());
        erro.setError("Controlador nao  achado");
        erro.setStatus(status.value());
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandardError> database(DataBaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError erro= new StandardError();
        erro.setTimeStamp(Instant.now());
        erro.setMessage(e.getMessage());
        erro.setError("violacao de dados");
        erro.setStatus(status.value());
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(erro);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> database(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError erro= new ValidationError();
        erro.setTimeStamp(Instant.now());
        erro.setMessage(e.getMessage());
        erro.setError("Validation exception");
        erro.setStatus(status.value());
        erro.setPath(request.getRequestURI());

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            erro.addError(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(erro);
    }
    @ExceptionHandler(CustomAccessDeniedException.class)
    public ResponseEntity<StandardError> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        StandardError erro = new StandardError();
        erro.setTimeStamp(Instant.now());
        erro.setMessage("Access is denied. You do not have the necessary permissions.");
        erro.setError("Forbidden");
        erro.setStatus(status.value());
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(erro);
    }
    @ExceptionHandler(CustomUnauthorizedException.class)
    public ResponseEntity<StandardError> handleUnauthorizedException(CustomUnauthorizedException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardError error = new StandardError();
        error.setTimeStamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Unauthorized");
        error.setMessage("Authentication is required to access this resource.");
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(error);
    }
}