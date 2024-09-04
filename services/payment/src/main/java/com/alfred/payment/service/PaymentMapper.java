package com.alfred.payment.service;

import com.alfred.payment.models.Payment;
import com.alfred.payment.models.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request) {
        return  Payment.builder()
                .id(request.id())
                .orderId(request.orderId())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .amount(request.amount())
                .build();
    }
}
