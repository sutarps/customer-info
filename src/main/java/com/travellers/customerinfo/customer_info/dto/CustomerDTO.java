package com.travellers.customerinfo.customer_info.dto;

import org.openapitools.model.Customer;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

public class CustomerDTO {

    Long customerId;
    String firstName;
    String lastName;
    Date dateOfBirth;
    String email;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    String phone;

    public CustomerDTO(){}

    public CustomerDTO(Customer customer){
        this.customerId = customer.getCustomerId();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.dateOfBirth = Date.valueOf(customer.getDateOfBirth());
        this.email = customer.getEmail();
        if(customer.getCreateAt()==null){
            this.createdAt = LocalDateTime.now();
        }else{
            this.createdAt = customer.getCreateAt().toLocalDateTime();
        }
        if(customer.getUpdatedAt() ==null){
            this.updatedAt = LocalDateTime.now();
        }else{
            this.updatedAt = customer.getUpdatedAt().toLocalDateTime();
        }
        this.phone = customer.getPhone();
    }

    public CustomerDTO(com.travellers.customerinfo.customer_info.dbmodel.Customer customer) {
        this.customerId = customer.customerId();
        this.firstName = customer.firstName();
        this.lastName = customer.lastName();
        this.dateOfBirth = customer.dateOfBirth();
        this.email = customer.email();
        this.createdAt = customer.createdAt();
        this.updatedAt = customer.updatedAt();
        this.phone = customer.phone();
    }

    public com.travellers.customerinfo.customer_info.dbmodel.Customer toCustomer(){
        return new com.travellers.customerinfo.customer_info.dbmodel.Customer(
                customerId,
                firstName,
                lastName,
                dateOfBirth,
                email,
                createdAt,
                updatedAt,
                phone
        );
    }
    public Customer toOpenAPIcustomer(){
        Customer newCustomer = new Customer();
        newCustomer.customerId(this.customerId);
        newCustomer.firstName(this.firstName);
        newCustomer.lastName(this.lastName);
        newCustomer.dateOfBirth(this.dateOfBirth.toLocalDate());
        newCustomer.email(this.email);
        newCustomer.createAt(this.createdAt.atOffset(ZoneOffset.UTC));
        newCustomer.updatedAt(this.updatedAt.atOffset(ZoneOffset.UTC));
        newCustomer.phone(this.phone);
        return newCustomer;
    }

}
