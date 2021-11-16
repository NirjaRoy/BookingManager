package com.api.BookingManagerApp.controller;

import com.api.BookingManagerApp.dto.CustomerResponse;
import com.api.BookingManagerApp.model.CustomerDetails;
import com.api.BookingManagerApp.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    public CustomerService customerService;

    @GetMapping("/")
    Collection<CustomerDetails> getCustomer(){
        return customerService.getCustomer();
    }

    @GetMapping("/{id}")
    public CustomerResponse getCustomer(@PathVariable Integer id){
        if(id == 0)
            throw new IllegalArgumentException("Invalid ID");
        return	customerService.getCustomerById(id);
    }

    @PostMapping("/create")
    public CustomerResponse addCustomer(@RequestBody CustomerDetails customerDetails)  throws Exception {
        log.info("Add Customer- request: {}",customerDetails);
        CustomerResponse response = customerService.addCustomer(customerDetails);
        log.info("Add Customer- response: {}",response);
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public CustomerResponse deleteCustomer(@PathVariable Integer id) {
        if(id == 0)
            throw new IllegalArgumentException("Invalid customer ID");
        return customerService.deleteCustomer(id);
    }

}
