package com.api.BookingManagerApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Document(collection = "Booking")
public class BookingDetails {
    @Id
    public int bookingCode;

    //@ManyToOne
    //@JoinColumn(name="customer_ID", nullable = false)
    private CustomerDetails customer;

    public String propertyCode;
    public Date bookingDate;
    public int bookingDays;
}
