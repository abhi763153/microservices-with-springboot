package com.abhi_app.ReviewMS.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleReviewNotFoundException(ReviewNotFoundException exception){
        ErrorResponse error =  new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Review is not available for what you are looking");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
