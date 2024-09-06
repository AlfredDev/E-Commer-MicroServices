package com.alfred.order.Controllers;

import com.alfred.order.DTO.OrdersLineResponse;
import com.alfred.order.Models.OrderLine.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLinceController {
    private final OrderLineService service;
    @GetMapping("/order/{order-id}")
    public ResponseEntity<List<OrdersLineResponse>> findByOrderId(
            @PathVariable("order-id") Integer orderId) {
        return ResponseEntity.ok(service.findByOrderId(orderId));
    }
}
