package com.java.bootdbdata.controller;


import com.java.bootdbdata.model.Customer;
import com.java.bootdbdata.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger logger = Logger.getLogger("CustomerController");

    @Autowired
    private CustomerService service;

    @GetMapping("/getCustomer/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") String customerId) throws Exception {
        try {
            Customer customer = service.getCustomerId(customerId);
            logger.info("Customer data " + customer);
            return customer;
        } catch (Exception e) {
            logger.info("Customer not found");
            throw new Exception(e);
        }

    }

}
