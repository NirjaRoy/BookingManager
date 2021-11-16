package com.api.BookingManagerApp.service;

import com.api.BookingManagerApp.dto.CustomerResponse;
import com.api.BookingManagerApp.model.CustomerDetails;

import java.util.List;

public interface CustomerService {
    CustomerResponse addCustomer(CustomerDetails customerDetails);
    List<CustomerDetails> getCustomer();
    CustomerResponse getCustomerById(int id);
    CustomerResponse deleteCustomer(int id);
}
