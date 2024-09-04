package com.alfred.order.kafka;

import com.alfred.order.Models.PaymentMethod;
import com.alfred.order.Models.customer.CustomerResponse;
import com.alfred.order.Models.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products
) {
}
