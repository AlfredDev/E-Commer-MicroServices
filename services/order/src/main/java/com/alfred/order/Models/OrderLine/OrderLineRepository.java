package com.alfred.order.Models.OrderLine;

import com.alfred.order.DTO.OrdersLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllOrderId(Integer orderId);
}