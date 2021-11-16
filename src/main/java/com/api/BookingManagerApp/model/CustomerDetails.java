package com.api.BookingManagerApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Document(collection = "Customer")
public class CustomerDetails {
    @Id
    public int customerID;
    public String customerName;
    public String customerContact;
    public String customerEmail;
}
