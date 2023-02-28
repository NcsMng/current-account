package com.ncsgab.currentaccount.controller;

import com.ncsgab.currentaccount.dto.response.GenericResponse;
import com.ncsgab.currentaccount.exception.CustomerNotFoundException;
import com.ncsgab.currentaccount.exception.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {CustomerNotFoundException.class})
    public ResponseEntity<GenericResponse<ErrorMessage>> handleNotFoundException(CustomerNotFoundException e) {
        log.error(e.getMessage(), e);
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());

        GenericResponse<ErrorMessage> response = new GenericResponse<>(errorMessage);
        log.warn("Sending response with httpStatus -> {} and body -> {}", HttpStatus.NOT_FOUND, response);
        return ResponseEntity.ok(response);
    }
}
