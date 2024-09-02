package com.alfred.customer.customer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document
public class Customer {
    @Id
    private String id;
    private String lastName;
    private String firstName;
    private String email;
    private Address address;
}
