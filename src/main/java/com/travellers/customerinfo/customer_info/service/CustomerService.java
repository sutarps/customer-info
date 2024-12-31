package com.travellers.customerinfo.customer_info.service;

import com.travellers.customerinfo.customer_info.dbmodel.Address;
import com.travellers.customerinfo.customer_info.dbmodel.ContactDetails;
import com.travellers.customerinfo.customer_info.dto.AddressDTO;
import com.travellers.customerinfo.customer_info.dto.ContactDetailsDTO;
import com.travellers.customerinfo.customer_info.dto.CustomerDTO;
import com.travellers.customerinfo.customer_info.exception.exceptions.CustomerExistException;
//import com.travellers.customerinfo.customer_info.repo.CustomerRepo;
import com.travellers.customerinfo.customer_info.dbmodel.Customer;
import com.travellers.customerinfo.customer_info.repo.AddressRepo;
import com.travellers.customerinfo.customer_info.repo.ContactsRepo;
import com.travellers.customerinfo.customer_info.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepo customerRepo;
    private final AddressRepo addressRepo;
    private final ContactsRepo contactsRepo;
    @Autowired
    public CustomerService(CustomerRepo customerRepo, AddressRepo addressRepo, ContactsRepo contactsRepo) {
        this.customerRepo = customerRepo;
        this.addressRepo = addressRepo;
        this.contactsRepo = contactsRepo;
    }

    public Optional<Customer> getCustomer(Long customerId){
        return customerRepo.findById(customerId);

    }

    public Customer createCustomer(Customer customer){
        customerRepo.findByemail(customer.email()).ifPresent(c ->{
            logger.warn("Customer with email {} already exists",customer.email());
            throw new CustomerExistException("Email "+customer.email() +" is already used");
        });
        Customer newCustomer = customerRepo.save(customer);
        logger.info("Customer create with id {}",newCustomer.customerId());
        return newCustomer;
    }

    public List<org.openapitools.model.Customer> getAllCustomers() {
         Iterator<Customer> customerIterable= customerRepo.findAll().iterator();
         List<org.openapitools.model.Customer> customerList = new ArrayList<>();
         while(customerIterable.hasNext()){
             Customer customer = customerIterable.next();
             customerList.add(new CustomerDTO(customer).toOpenAPIcustomer()
                     .contactDetails(getContacts(customer.customerId()))
                     .addresses(getAddresses(customer.customerId())));
         }
         return customerList;
    }
    public List<org.openapitools.model.ContactDetails> createContact(List<ContactDetailsDTO> contactDetailsDTOList){
        return contactDetailsDTOList.stream()
                .map(contactDTO -> {
                    ContactDetails contactDetails = contactDTO.contactDetails();
                     contactDetails = contactsRepo.save(contactDetails);
                     return new ContactDetailsDTO(contactDetails).toContactDetails();
                }).collect(Collectors.toList());
    }
    public List<org.openapitools.model.ContactDetails> getContacts(Long customerId){
        Optional<List<ContactDetails>> contactDetailsList = contactsRepo.findByCustomerId(customerId);
        List<org.openapitools.model.ContactDetails> contactDetails = new ArrayList<>();
        if(contactDetailsList.isEmpty())
            return contactDetails;
        List<ContactDetailsDTO> contactDetailsDTOList = new ArrayList<>();
        contactDetailsDTOList = contactDetailsList.get().stream()
                .map(contact -> {
                    return new ContactDetailsDTO(contact);
                })
                .collect(Collectors.toList());
        contactDetails = contactDetailsDTOList.stream().map(c->{
            return c.toContactDetails();
        }).collect(Collectors.toList());
        return contactDetails;
    }
    public List<org.openapitools.model.Address> createAddress(List<AddressDTO> addressDTOList){
        return addressDTOList.stream()
                .map(addressDTO -> {
                    Address address = addressDTO.toDbAddress();
                    address = addressRepo.save(address);
                    return new AddressDTO(address).toAddress();
                }).collect(Collectors.toList());
    }
    public List<org.openapitools.model.Address> getAddresses(Long custoerId){
        Optional<List<Address>> addressList = addressRepo.findByCustomerId(custoerId);
        List<org.openapitools.model.Address> addresses = new ArrayList<>();
        if(addressList.isEmpty())
            return addresses;
        List<AddressDTO> addressDTOList = new ArrayList<>();
        addressDTOList = addressList.get().stream()
                .map(address -> {
                    return new AddressDTO(address);
                }).collect(Collectors.toList());
        addresses = addressDTOList.stream()
                .map(addr -> {
                    return addr.toAddress();
                }).collect(Collectors.toList());
        return addresses;
    }
}
