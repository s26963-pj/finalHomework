package com.project.finalHomework;

import com.project.finalHomework.exceptions.ExchangeNotFoundInNbpException;
import com.project.finalHomework.exceptions.notValidNumberOfDays;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExchangeAdvice extends RuntimeException{

    @ExceptionHandler(ExchangeNotFoundInNbpException.class)
    public ResponseEntity<String> exchangeNotFoundInNbpException(ExchangeNotFoundInNbpException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Exception occured on request. Exception message: " + ex.getLocalizedMessage());
    }

    @ExceptionHandler(notValidNumberOfDays.class)
    public ResponseEntity<String> notValidNumberOfDaysException(notValidNumberOfDays ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Exception occured on request. Exception message: " + ex.getLocalizedMessage());
    }

}
