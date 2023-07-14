package com.esg.demo.ESG.Demo.csvutil;

import com.esg.demo.ESG.Demo.customer.Customer;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {

    /**
     * @should return list of customer
     * @should throw exception with invalid data
     */
    public List<Customer> getDataFromCsv(String filePath) {

        String line;
        List<Customer> customers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                Customer customer = new Customer();
                customer.setCustomerRef(Long.valueOf(data[0]));
                customer.setCustomerName(data[1]);
                customer.setAddressLine1(data[2]);
                customer.setAddressLine2(data[3]);
                customer.setTown(data[4]);
                customer.setCounty(data[5]);
                customer.setCountry(data[6]);
                customer.setPostcode(data[7]);

                customers.add(customer);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }

        return customers;
    }

    public void sendDataToAPI(List<Customer> customers) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            customers.forEach(customer -> restTemplate.
                    postForEntity("http://localhost:8080/api/Customer/create", customer, String.class));
        } catch (RuntimeException e) {
            throw new RuntimeException("Could not save to the database: \n" + e);
        }

    }

}
