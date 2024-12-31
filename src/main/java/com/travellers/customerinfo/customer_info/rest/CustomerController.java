package com.travellers.customerinfo.customer_info.rest;


import com.travellers.customerinfo.customer_info.dto.AddressDTO;
import com.travellers.customerinfo.customer_info.dto.ContactDetailsDTO;
import com.travellers.customerinfo.customer_info.dto.CustomerDTO;
import com.travellers.customerinfo.customer_info.exception.exceptions.NotFoundException;
import com.travellers.customerinfo.customer_info.service.CustomerService;
import org.openapitools.api.CustomersApi;
import org.openapitools.model.Address;
import org.openapitools.model.ContactDetails;
import org.openapitools.model.Customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class CustomerController implements CustomersApi {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Override
    public ResponseEntity<Customer> createCustomer(Customer customer) {
        com.travellers.customerinfo.customer_info.dbmodel.Customer newCust = customerService.createCustomer(new CustomerDTO(customer).toCustomer());
        List<ContactDetailsDTO> contactDetailsDTOList = new ArrayList<>();
        List<ContactDetails> contacts = new ArrayList<>();
        if(!customer.getContactDetails().isEmpty()){
            contactDetailsDTOList = customer.getContactDetails().stream()
                    .map(contactDetails -> {
                        ContactDetailsDTO contactDetailsDTO = new ContactDetailsDTO(contactDetails);
                        contactDetailsDTO.setCustomerId(newCust.customerId());
                        return contactDetailsDTO;
                    })
                    .collect(Collectors.toList());
            contacts = customerService.createContact(contactDetailsDTOList);
        }
        List<AddressDTO> addressDTOList = new ArrayList<>();
        List<Address> addresses = new ArrayList<>();
        if (!customer.getAddresses().isEmpty()) {
            addressDTOList = customer.getAddresses().stream()
                    .map(address -> {
                        AddressDTO addressDTO = new AddressDTO(address);
                        addressDTO.setCustomerId(newCust.customerId());
                        return addressDTO;
                    }).collect(Collectors.toList());
            addresses = customerService.createAddress(addressDTOList);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(new CustomerDTO(newCust).toOpenAPIcustomer().contactDetails(contacts).addresses(addresses));
    }

    @Override
    public ResponseEntity<String> deleteCustomer(String customerId) {
        return null;
    }

    @Override
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customerList = customerService.getAllCustomers();
        if(customerList.isEmpty()){
            logger.warn("No customer found in database");
            throw new NotFoundException("No customer exist in system");
        }
        logger.info("Total customer found {}",customerList.size());
        return ResponseEntity.status(HttpStatus.OK).body(customerList);
    }

    @Override
    public ResponseEntity<Customer> getCustomer(Long customerId) {
        Optional<com.travellers.customerinfo.customer_info.dbmodel.Customer> customer= customerService.getCustomer(customerId);
        if(customer.isEmpty()){
            logger.warn("Customer not found with id {}",customerId);
            throw new NotFoundException("Customer not found");
        }
        logger.info("Customer found {}",customerId);
        return ResponseEntity.status(HttpStatus.OK).body(new CustomerDTO(customer.get()).toOpenAPIcustomer()
                .contactDetails(customerService.getContacts(customerId))
                .addresses(customerService.getAddresses(customerId)));
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(String customerId, Customer customer) {
        return null;
    }
}


