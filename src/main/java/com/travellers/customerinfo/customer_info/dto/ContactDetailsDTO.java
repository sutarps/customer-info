package com.travellers.customerinfo.customer_info.dto;

import lombok.Data;
import org.openapitools.model.ContactDetails;

public class ContactDetailsDTO {
    Long contactId;
    Long customerId;
    String email;
    String phone;
    ContactDetails.ContactTypeEnum contactType;

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setContactType(ContactDetails.ContactTypeEnum contactType) {
        this.contactType = contactType;
    }

    public ContactDetailsDTO(ContactDetails contactDetails){
        this.contactId = contactDetails.getContactId();
        this.customerId = contactDetails.getCustomerId();
        this.email = contactDetails.getEmail();
        this.phone = contactDetails.getPhone();
        this.contactType = contactDetails.getContactType();
    }
    public ContactDetailsDTO(com.travellers.customerinfo.customer_info.dbmodel.ContactDetails contactDetails){
        this.contactId = contactDetails.contactId();
        this.customerId = contactDetails.customerId();
        this.email = contactDetails.email();
        this.phone = contactDetails.phone();
        this.contactType = ContactDetails.ContactTypeEnum.fromValue(contactDetails.contactType());
    }
    public com.travellers.customerinfo.customer_info.dbmodel.ContactDetails contactDetails(){
        return new com.travellers.customerinfo.customer_info.dbmodel.ContactDetails(this.contactId,
                this.customerId,this.email,this.phone,this.contactType.getValue());
    }
    public ContactDetails toContactDetails(){
        ContactDetails contactDetails = new ContactDetails();
        contactDetails.contactId(this.contactId);
        contactDetails.customerId(this.customerId);
        contactDetails.email(this.email);
        contactDetails.phone(this.phone);
        contactDetails.contactType(this.contactType);
        return contactDetails;
    }
}
