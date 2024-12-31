package com.travellers.customerinfo.customer_info.repo;

import com.travellers.customerinfo.customer_info.dbmodel.Address;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface AddressRepo extends CrudRepository<Address, Long> {
   Optional<List<Address>> findByCustomerId(Long customerId);

}
