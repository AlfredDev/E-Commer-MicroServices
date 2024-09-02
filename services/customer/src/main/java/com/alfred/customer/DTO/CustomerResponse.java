package com.alfred.customer.DTO;

import com.alfred.customer.customer.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerResponse(
        String id,
        String lastName,
        String firstName,
        String email,
        Address address
) {
}
