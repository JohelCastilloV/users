package com.sermaluc.user.controller;

import com.sermaluc.user.exception.GlobalException;
import com.sermaluc.user.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<GlobalException> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        GlobalException globalException = new GlobalException();
        globalException.setMensaje(ex.getMessage());
        return new ResponseEntity<>(globalException, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        GlobalException globalException = new GlobalException();
        if(Objects.nonNull(ex.getFieldError())){
            globalException.setMensaje(ex.getFieldError().getDefaultMessage());
        }else {
            globalException.setMensaje(ex.getMessage());
        }
        return new ResponseEntity<>(globalException, HttpStatus.BAD_REQUEST);
    }
}
