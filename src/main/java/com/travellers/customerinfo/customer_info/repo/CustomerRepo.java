package com.travellers.customerinfo.customer_info.repo;

import com.travellers.customerinfo.customer_info.dbmodel.Customer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CustomerRepo extends CrudRepository<Customer, Long> {
//    @Query("SELECT * FROM customer WHERE email =: email")
//    Optional<Object> findByEmail(@Param("email") String email);
    Optional<Object> findByemail(String email);
}
