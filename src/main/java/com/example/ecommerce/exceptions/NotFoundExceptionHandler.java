package com.example.ecommerce.exceptions;

import com.example.ecommerce.errorResponses.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;

public class NotFoundExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> playerNotFoundHandler (NotFoundException ex, HttpServletRequest req){

        ExceptionResponse error = new ExceptionResponse(
                ZonedDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                req.getRequestURI(),
                ex.getMessage());

        return new ResponseEntity<> (error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> genericHandler (Exception ex, HttpServletRequest req){
        ExceptionResponse error = new ExceptionResponse(
                ZonedDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                req.getRequestURI(),
                ex.getMessage());

        return new ResponseEntity<> (error, HttpStatus.BAD_REQUEST);
    }
}
