package com.travellers.customerinfo.customer_info.dbmodel;

import org.springframework.data.annotation.Id;

public record Address(
        @Id
        Long addressId,
        String addressType,
        Long customerId,
        String houseNo,
        String street,
        String city,
        String state,
        String postalCode,
        String country
) {
}
