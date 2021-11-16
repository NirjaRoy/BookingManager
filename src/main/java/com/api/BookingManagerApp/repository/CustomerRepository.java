package com.api.BookingManagerApp.repository;

import com.api.BookingManagerApp.model.CustomerDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerDetails, Integer> {
}
