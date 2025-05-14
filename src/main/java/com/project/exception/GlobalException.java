package com.project.exception;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalException{

    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<Map<String,Object>>handleNotFound(CandidateNotFoundException ex) {
        Map<String,Object> response = Map.of(
                "timestamp" , LocalTime.now().toString(),
                "status", HttpStatus.NOT_FOUND.value(),
                "error","Candidate Not Found",
                "message", ex.getMessage(),
                "path", "/candidates/"+ ex.getMessage()

        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String,Object>> handleBadRequest(BadRequestException ex){
        Map<String,Object> response = Map.of(
                "timestamp",LocalTime.now().toString(),
                "status",HttpStatus.BAD_REQUEST,
                "error","Invalid Status",
                "message",ex.getMessage(),
                "path", "/candidates/"+ ex.getMessage()

        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

}
