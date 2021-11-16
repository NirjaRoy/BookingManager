package com.api.BookingManagerApp.controller;

import com.api.BookingManagerApp.service.BookingService;
import com.api.BookingManagerApp.dto.BookingRequest;
import com.api.BookingManagerApp.dto.BookingResponse;
import com.api.BookingManagerApp.dto.CustomerResponse;
import com.api.BookingManagerApp.model.BookingDetails;
import com.api.BookingManagerApp.model.CustomerDetails;
import com.api.BookingManagerApp.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;

@RestController
@RequestMapping("/booking")
public class BookingController {
    Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    public BookingService bookingService;

    @GetMapping("/{id}")
    public BookingResponse getBooking(@PathVariable Integer id){
        if(id == 0)
            throw new IllegalArgumentException("Invalid ID");
        return	bookingService.getBooking(id);
    }

    @PostMapping("/create")
    public BookingResponse createBooking(@RequestBody BookingRequest bookingRequest) {
        log.info("Create Booking- request: {}",bookingRequest);
        BookingResponse bookingResponse = bookingService.createBooking(bookingRequest);
        log.info("Create Booking- response: {}",bookingResponse);
        return bookingResponse;
    }

    @PutMapping("/update")
    public BookingResponse updateBooking(@RequestBody BookingRequest request) {
        log.info("Update Booking- request: {}",request);
        BookingResponse response = bookingService.updateBooking(request);
        log.info("Update Booking- response: {}",response);
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public BookingResponse deleteBooking(@PathVariable Integer id) {
        if(id == 0)
            throw new IllegalArgumentException("Invalid booking ID");
        return bookingService.deleteBooking(id);
    }
}
