package com.alfred.order.Models.customer;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
        ) {
}
