package com.joao.demoparkapi.web.excepition;

import com.joao.demoparkapi.exception.EntityNotFoundExcepiton;
import com.joao.demoparkapi.exception.PasswordInvalidException;
import com.joao.demoparkapi.exception.UsernameUniqueViolationExeption;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiExcepitionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMensage> methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                        HttpServletRequest request,
                                                                        BindingResult result){
        log.error("Api Error, ",ex);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMensage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campo(s) invalido(s)",result));
    }

    @ExceptionHandler(UsernameUniqueViolationExeption.class)
    public ResponseEntity<ErrorMensage> uniqueViolationExeption(RuntimeException ex, HttpServletRequest request){
        log.error("Api Error, ",ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMensage(request, HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage()));
    }
    @ExceptionHandler(EntityNotFoundExcepiton.class)
    public ResponseEntity<ErrorMensage> entityNotFoundExcepition(RuntimeException ex, HttpServletRequest request){
        log.error("Api Error, ",ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMensage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }
    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorMensage> passwordInvalidException(RuntimeException ex, HttpServletRequest request){
        log.error("Api Error, ",ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMensage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

}
