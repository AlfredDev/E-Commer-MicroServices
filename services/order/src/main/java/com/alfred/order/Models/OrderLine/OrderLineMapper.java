package com.alfred.order.Models.OrderLine;

import com.alfred.order.DTO.OrdersLineResponse;
import com.alfred.order.Models.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {


    public OrderLine toOrderLine(OderLineRequest request) {
        return  OrderLine.builder()
                .id(request.id())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .productId(request.productId())
                .build();
    }

    public OrdersLineResponse toOrdersLineResponse(OrderLine orderLine) {
        return new  OrdersLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
