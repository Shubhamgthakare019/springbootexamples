package com.java.bootdbdata.service;

import com.java.bootdbdata.model.Customer;
import com.java.bootdbdata.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = {ServiceMockitoTests.class})
public class ServiceMockitoTests {

    @Mock
    CustomerRepository repository;

    @InjectMocks
    CustomerService service;

    public Customer customer;

    @Test
    public void test_getCustomerIdHappyFlow() throws Exception {
        customer = new Customer();
        customer.setCustomerId("1");
        customer.setCustomerName("John");

        when(repository.findByCustomerId(customer.getCustomerId())).thenReturn(customer);
        Assertions.assertEquals(customer, service.getCustomerId(customer.getCustomerId()));
    }

    @Test
    public void test_getCustomerIdUnhappyFlow() throws Exception {
        customer = new Customer();
        customer.setCustomerId("1");
        customer.setCustomerName("John");

        Customer customer1 = new Customer();
        customer1.setCustomerId("2");
        customer1.setCustomerName("Sena");
        when(repository.findByCustomerId(customer1.getCustomerId())).thenReturn(customer);
        Assertions.assertNotEquals(customer1, service.getCustomerId(customer.getCustomerId()));
    }

}
