package com.java.bootdbdata.controller;

import com.java.bootdbdata.model.Customer;
import com.java.bootdbdata.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = {CustomerControllerTest.class})
public class CustomerControllerTest {

    @Mock
    CustomerService service;

    @InjectMocks
    CustomerController controller;

    public Customer customer;

    @Test
    public void test_getCustomer() throws Exception {
        customer = new Customer();
        customer.setCustomerId("1");
        customer.setCustomerName("John");
        when(service.getCustomerId(customer.getCustomerId())).thenReturn(customer);
        Assertions.assertEquals(customer,controller.getCustomer(customer.getCustomerId()));

    }

}
