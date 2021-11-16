package com.api.BookingManagerApp.service;

import com.api.BookingManagerApp.dto.BookingRequest;
import com.api.BookingManagerApp.dto.BookingResponse;
import com.api.BookingManagerApp.model.BookingDetails;

import java.util.List;

public interface BookingService {
    List<BookingDetails> getBooking();
    BookingResponse getBooking(int id);
    BookingResponse createBooking(BookingRequest bookingRequest);
    BookingResponse updateBooking(BookingRequest bookingRequest);
    BookingResponse deleteBooking(int id);
}
