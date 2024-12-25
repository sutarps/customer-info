package com.travellers.customerinfo.customer_info.exception.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class NotFoundException extends HttpClientErrorException {
    public NotFoundException(String errorMessage){
        super(HttpStatus.NOT_FOUND,errorMessage);
    }
}
