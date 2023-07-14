package com.esg.demo.ESG.Demo.customer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService service;

    @Autowired
    private CustomerRepository repository;

    @BeforeEach
    void beforeEach() {
        repository.deleteAll();
    }


    /**
     * @verifies return customer from db
     */
    @Test
    public void getCustomer_shouldReturnCustomerFromDb() {
        // given
        Customer customer = new Customer();
        customer.setCustomerRef(1L);

        // when
        repository.save(customer);
        Customer result = service.getCustomer("1");

        // then
        Assertions.assertEquals(1L, result.getCustomerRef());
    }
}
