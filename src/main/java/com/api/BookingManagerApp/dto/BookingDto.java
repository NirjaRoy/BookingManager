package com.api.BookingManagerApp.dto;

import com.api.BookingManagerApp.model.CustomerDetails;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDto {
    public int bookingCode;
    private int customerID;
    public String propertyCode;
    public Date bookingDate;
    public int bookingDays;
}
