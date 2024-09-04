package com.alfred.order.payment;

import com.alfred.order.Models.PaymentMethod;
import com.alfred.order.Models.customer.CustomerResponse;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,

        Integer orderId,
        String orderReference,
        CustomerResponse customer) {
}
