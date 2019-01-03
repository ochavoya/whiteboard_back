package com.ochavoya.whiteboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class JsonExceptionHandler {
    @ExceptionHandler(value = JsonProcessingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)  // 409
    public void jsonProcessingExceptionHandler() {
    }
}
