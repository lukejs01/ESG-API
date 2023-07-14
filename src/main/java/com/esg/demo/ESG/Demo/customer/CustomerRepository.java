package com.esg.demo.ESG.Demo.customer;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findBycustomerRef(Long customerRef);
}
