package com.api.BookingManagerApp.dto;

import com.api.BookingManagerApp.model.BookingDetails;
import com.api.BookingManagerApp.model.CustomerDetails;
import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponse {
    private BookingDto bookingDto;
    private HttpStatus statusCode;
    private String message;
}
