package com.java.bootdbdata.service;

import com.java.bootdbdata.model.Customer;
import com.java.bootdbdata.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class CustomerService {

    @Autowired
    CustomerRepository repository;

    public Customer getCustomerId(String id) throws Exception {
       Customer customer=repository.findByCustomerId(id);
        return customer;
    }

}

