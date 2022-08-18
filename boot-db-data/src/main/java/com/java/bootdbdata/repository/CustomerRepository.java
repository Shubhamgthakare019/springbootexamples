package com.java.bootdbdata.repository;

import com.java.bootdbdata.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Customer findByCustomerId(String countryId) throws Exception;
}