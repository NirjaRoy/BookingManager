package com.api.BookingManagerApp.service;

import com.api.BookingManagerApp.dto.CustomerResponse;
import com.api.BookingManagerApp.model.CustomerDetails;
import com.api.BookingManagerApp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    public CustomerRepository customerRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public CustomerResponse addCustomer(CustomerDetails customerDetails) {
        Query query = new Query();
        query.addCriteria(Criteria.where("customerID").is(customerDetails.customerID));
        List<CustomerDetails> existingCustomer = mongoTemplate.find(query, CustomerDetails.class);

        if(!(existingCustomer.isEmpty()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer already exists");

        customerRepository.save(customerDetails);
        CustomerDetails details = customerRepository.findById(customerDetails.customerID).get();

        return CustomerResponse.builder()
                .customerDetails(details)
                .statusCode(HttpStatus.CREATED)
                .message("Customer added successfully with ID- "+customerDetails.customerID)
                .build();
    }

    @Override
    public List<CustomerDetails> getCustomer() {
        List<CustomerDetails> data = mongoTemplate.findAll(CustomerDetails.class);

        if(data.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No customer details available");
        return data;
    }

    @Override
    public CustomerResponse getCustomerById(int id) {
        CustomerDetails details = customerRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"No customer exists with ID- "+id));

        return CustomerResponse.builder()
                .customerDetails(details)
                .statusCode(HttpStatus.OK)
                .message("Customer fetched successfully")
                .build();
    }

    @Override
    public CustomerResponse deleteCustomer(int id) {
        CustomerDetails details = customerRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.BAD_REQUEST,"Please enter valid Customer ID"));

        mongoTemplate.remove(new Query(Criteria.where("customerID").is(id)), CustomerDetails.class);

        return CustomerResponse.builder()
                .statusCode(HttpStatus.ACCEPTED)
                .message("Customer deleted successfully")
                .build();
    }


}
