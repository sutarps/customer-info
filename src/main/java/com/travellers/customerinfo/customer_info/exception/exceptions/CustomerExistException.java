package com.travellers.customerinfo.customer_info.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

public class CustomerExistException extends HttpClientErrorException {
    public CustomerExistException(String errorMessage){
        super(HttpStatus.CONFLICT,errorMessage);
    }
}
