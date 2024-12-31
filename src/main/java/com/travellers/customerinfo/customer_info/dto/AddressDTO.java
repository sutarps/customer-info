package com.travellers.customerinfo.customer_info.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.openapitools.model.Address;

public class AddressDTO {
    Long addressId;
    Address.AddressTypeEnum addressType;
    Long customerId;
    String houseNo;
    String street;
    String city;
    String state;
    String postalCode;
    String country;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Address.AddressTypeEnum getAddressType() {
        return addressType;
    }

    public void setAddressType(Address.AddressTypeEnum addressType) {
        this.addressType = addressType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public AddressDTO(com.travellers.customerinfo.customer_info.dbmodel.Address address){
        this.addressId = address.addressId();
        this.customerId = address.customerId();
        this.houseNo = address.houseNo();
        this.street = address.street();
        this.city = address.city();
        this.state = address.state();
        this.country = address.country();
        this.postalCode = address.postalCode();
        this.addressType = Address.AddressTypeEnum.fromValue(address.addressType());
    }
    public AddressDTO(Address address){
        this.addressId = address.getAddressId();
        this.addressType = address.getAddressType();
        this.customerId = address.getCustomerId();
        this.houseNo = address.getHouseNo();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.state  = address.getState();
        this.country = address.getCountry();
        this.postalCode = address.getPostalCode();
    }
    public com.travellers.customerinfo.customer_info.dbmodel.Address toDbAddress(){
        return new com.travellers.customerinfo.customer_info.dbmodel.Address(this.addressId,
                this.addressType.getValue(),
                this.customerId,
                this.houseNo,
                this.street,
                this.city,
                this.state,
                this.postalCode,this.country);
    }
    public Address toAddress(){
        Address address = new Address();
        address.setAddressId(this.addressId);
        address.setAddressType(this.addressType);
        address.setCustomerId(this.customerId);
        address.setHouseNo(this.houseNo);
        address.setStreet(this.street);
        address.setCity(this.city);
        address.setState(this.state);
        address.setCountry(this.country);
        address.setPostalCode(this.postalCode);
        return address;
    }
}
