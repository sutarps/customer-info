package com.travellers.customerinfo.customer_info.dbmodel;

import org.springframework.data.annotation.Id;

public record ContactDetails(
        @Id
        Long contactId,
        Long customerId,
        String email,
        String phone,
        String contactType

) {
}
