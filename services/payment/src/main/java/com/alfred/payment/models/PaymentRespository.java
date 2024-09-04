package com.alfred.payment.models;

import com.alfred.payment.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRespository extends JpaRepository<Payment,Integer> {
}
