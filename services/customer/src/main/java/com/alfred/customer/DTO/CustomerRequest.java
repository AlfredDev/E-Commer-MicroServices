package com.alfred.customer.DTO;

import com.alfred.customer.customer.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer Last nane is required")
        String lastName,
        @NotNull(message = "Customer First nane is required")
        String firstName,
        @NotNull(message = "Customer email is required")
        @Email(message = "Customer mail is not a valid email address")
        String email,
        Address address
) {
}
