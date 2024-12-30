package com.travellers.customerinfo.customer_info.repo;

import com.travellers.customerinfo.customer_info.dbmodel.ContactDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface ContactsRepo extends CrudRepository<ContactDetails, Long> {
    Optional<List<ContactDetails>> findByCustomerId(Long customerId);
    Optional<ContactDetails> findByEmail(String email);
    Optional<ContactDetails> findByPhone(String phone);
}
