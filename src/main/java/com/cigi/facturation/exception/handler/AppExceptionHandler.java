package com.cigi.facturation.exception.handler;

import com.cigi.facturation.exception.EntityNotFoundException;
import com.cigi.facturation.exception.ErrorMessage;
import com.cigi.facturation.exception.UnableToSaveEntityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class AppExceptionHandler {

    public static Logger log= LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFoundException(EntityNotFoundException ex) {
        ErrorMessage errMsg = ErrorMessage.builder().Message(ex.getMessage()).time(new Date()).code(404).build();
        log.error(errMsg.getMessage());
        return new ResponseEntity<>(errMsg, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(value = {UnableToSaveEntityException.class})
    public ResponseEntity<Object> UnableToSaveEntityException(UnableToSaveEntityException ex) {
        ErrorMessage errMsg = ErrorMessage.builder().Message(ex.getMessage()).time(new Date()).code(404).build();
        log.error(errMsg.getMessage());
        return new ResponseEntity<>(errMsg, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {IllegalArgumentException.class })
    public ResponseEntity<Object> IllegalArgumentException(IllegalArgumentException ex) {
        ErrorMessage errMsg = ErrorMessage.builder().Message("Bad Request").time(new Date()).code(400).build();
        log.error(errMsg.getMessage());
        return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(value = { HttpMessageNotReadableException.class})
    public ResponseEntity<Object> HttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorMessage errMsg = ErrorMessage.builder().Message("Bad Request").time(new Date()).code(400).build();
        log.error(errMsg.getMessage());
        return new ResponseEntity<>(errMsg,HttpStatus.BAD_REQUEST);

    }
}