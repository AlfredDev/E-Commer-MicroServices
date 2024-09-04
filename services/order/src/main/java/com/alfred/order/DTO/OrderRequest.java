package com.alfred.order.DTO;

import com.alfred.order.Models.PaymentMethod;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        Integer id,
        String reference,
        @Positive(message = "Order amount must be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method must be specified")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer must be present")
        @NotEmpty(message = "Customer must be present")
        @NotBlank(message = "Customer must be present")
        String customerId,
        @NotEmpty(message = "Shuld at least one or more products m")
        List<PurchaseRequest> products
) {
}
