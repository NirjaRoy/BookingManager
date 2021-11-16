package com.api.BookingManagerApp.service;

import com.api.BookingManagerApp.dto.BookingDto;
import com.api.BookingManagerApp.dto.BookingRequest;
import com.api.BookingManagerApp.dto.BookingResponse;
import com.api.BookingManagerApp.model.BookingDetails;
import com.api.BookingManagerApp.model.CustomerDetails;
import com.api.BookingManagerApp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService{

    @Autowired
    public BookingRepository bookingRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public BookingResponse createBooking(BookingRequest bookingRequest) {
        List<BookingDetails> existingBooking = mongoTemplate.find(
                new Query(Criteria.where("bookingCode").is(bookingRequest.getBookingDto().bookingCode)), BookingDetails.class);
        if(!(existingBooking.isEmpty()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Booking already exists");

        BookingDetails details = convertBookingRequestToBookingDetails(bookingRequest);
        mongoTemplate.save(details);

        BookingDto bookingDto = convertBookingDetailsToBookingDTO(details);

        return BookingResponse.builder()
                .bookingDto(bookingDto)
                .statusCode(HttpStatus.CREATED)
                .message("Booking done!")
                .build();    }

    @Override
    public List<BookingDetails> getBooking() {
        List<BookingDetails> data = mongoTemplate.findAll(BookingDetails.class);

        if(data.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No booking available");
        return data;
    }

    @Override
    public BookingResponse getBooking(int id) {
        BookingDetails details = mongoTemplate.findById(id,BookingDetails.class);
        if(details == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Booking details exists with ID- "+id);

        BookingDto bookingDto = convertBookingDetailsToBookingDTO(details);

        return BookingResponse.builder()
                .bookingDto(bookingDto)
                .statusCode(HttpStatus.OK)
                .message("Booking details fetched successfully")
                .build();
    }

    @Override
    public BookingResponse updateBooking(BookingRequest bookingRequest) {
        int bookingCode = bookingRequest.getBookingDto().bookingCode;
        BookingDetails existingBooking = mongoTemplate.findById(bookingCode,BookingDetails.class);
        if(existingBooking == null)
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Please enter valid booking code to update- "+bookingCode);

        BookingDetails details = convertBookingRequestToBookingDetails(bookingRequest);
        mongoTemplate.save(details);
        BookingDto bookingDto = convertBookingDetailsToBookingDTO(details);
        return BookingResponse.builder()
                .bookingDto(bookingDto)
                .statusCode(HttpStatus.OK)
                .message("Booking updated successfully")
                .build();
    }

    @Override
    public BookingResponse deleteBooking(int id) {
        BookingDetails existingBooking = mongoTemplate.findById(id,BookingDetails.class);
        if(existingBooking == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Kindly enter valid booking code to delete.");
        mongoTemplate.remove(new Query(Criteria.where("bookingCode").is(id)), BookingDetails.class);
        return BookingResponse.builder()
                .statusCode(HttpStatus.ACCEPTED)
                .message("Booking cancelled successfully")
                .build();
    }

    private BookingDetails convertBookingRequestToBookingDetails(BookingRequest request) {
        CustomerDetails customerDetails = CustomerDetails.builder()
                .customerID(request.getBookingDto().getCustomerID())
                .build();

        return BookingDetails.builder()
                .bookingCode(request.getBookingDto().getBookingCode())
                .bookingDate(request.getBookingDto().getBookingDate())
                .bookingDays(request.getBookingDto().getBookingDays())
                .propertyCode(request.getBookingDto().getPropertyCode())
                .customer(customerDetails)
                .build();
    }

    private BookingDto convertBookingDetailsToBookingDTO(BookingDetails bookingDetails) {
        return BookingDto.builder()
                .bookingCode(bookingDetails.getBookingCode())
                .bookingDate(bookingDetails.getBookingDate())
                .customerID(bookingDetails.getCustomer().getCustomerID())
                .bookingDays(bookingDetails.getBookingDays())
                .propertyCode(bookingDetails.getPropertyCode())
                .build();
    }
}
