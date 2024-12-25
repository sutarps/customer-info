package com.travellers.customerinfo.customer_info.dbmodel;

import org.springframework.data.annotation.Id;

import java.sql.Date;
import java.time.LocalDateTime;

public record Customer(
        @Id
        Long customerId,
        String firstName,
        String lastName,
        Date dateOfBirth,
        String email,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String phone
) {
}
