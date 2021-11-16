package com.api.BookingManagerApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<BookingRepository, Integer> {
}
