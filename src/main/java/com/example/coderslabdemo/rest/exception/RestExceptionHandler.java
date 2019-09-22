package com.example.coderslabdemo.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {

    private static final String MESSAGE = "message";
    private static final String STATUS = "status";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(HttpServletRequest req, Exception e) {
        final String message = e.getMessage();
        Map<String, String> fields = new HashMap<>();
        fields.put(MESSAGE, message);
        fields.put(STATUS, HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(fields, HttpStatus.BAD_REQUEST);

    }
}
