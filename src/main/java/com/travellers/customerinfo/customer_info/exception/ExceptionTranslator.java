package com.travellers.customerinfo.customer_info.exception;

import com.travellers.customerinfo.customer_info.exception.exceptions.CustomerExistException;
import com.travellers.customerinfo.customer_info.exception.exceptions.NotFoundException;
import org.openapitools.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionTranslator {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> handleNotFoundException(NotFoundException e){
        Error error = new Error();
        error.errorCode(404);
        error.errorMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(CustomerExistException.class)
    public ResponseEntity<Error> handleCustomerExistException(CustomerExistException ce){
        Error error = new Error();
        error.errorCode(409);
        error.errorMessage(ce.getMessage());
        return new ResponseEntity<>(error,HttpStatus.CONFLICT);
    }
}
