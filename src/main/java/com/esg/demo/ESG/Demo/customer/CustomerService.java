package com.esg.demo.ESG.Demo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;


    public void saveCustomer(Customer customer) {
        repository.save(customer);
    }


    /**
     * @should return customer from db
     */
    public Customer getCustomer(String customerRef) {

        try {
            Long customerRefLong = Long.valueOf(customerRef);
            Customer customer = repository.findBycustomerRef(customerRefLong);

            if (customer == null) {
                throw new NullPointerException();
            }
            return customer;
        } catch (NullPointerException e) {
            throw new NullPointerException("No record in db found for ref: " + customerRef);

        }

    }
}
