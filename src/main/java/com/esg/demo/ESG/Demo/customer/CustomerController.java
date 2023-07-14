package com.esg.demo.ESG.Demo.customer;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api/Customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    /**
     * @should save records from csv
     */
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody Customer customer) {
        service.saveCustomer(customer);
    }

    /**
     * @should retrieve customer by ref
     * @should return 404 if customer not found
     * @should return response with error message
     */
    @GetMapping(value = "/byRef")
    @ResponseBody
    public Customer getCustomer(HttpServletRequest request) {
        String cusRefHeader = request.getHeader("customerRef");
        return service.getCustomer(cusRefHeader);

    }
}
