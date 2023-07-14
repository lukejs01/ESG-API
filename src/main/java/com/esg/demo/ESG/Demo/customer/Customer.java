package com.esg.demo.ESG.Demo.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customer {

    @Id
    private Long customerRef;

    private String customerName;

    private String addressLine1;

    private String addressLine2;

    private String town;

    private String county;

    private String country;

    private String postcode;

}
