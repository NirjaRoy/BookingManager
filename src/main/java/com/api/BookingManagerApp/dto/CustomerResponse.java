package com.api.BookingManagerApp.dto;

import com.api.BookingManagerApp.model.CustomerDetails;
import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponse {
    private CustomerDetails customerDetails;
    private HttpStatus statusCode;
    private String message;
}
