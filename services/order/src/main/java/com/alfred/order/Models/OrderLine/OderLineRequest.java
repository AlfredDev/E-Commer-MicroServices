package com.alfred.order.Models.OrderLine;

public record OderLineRequest(Integer id,
                              Integer orderId,
                              Integer productId,
                              double quantity) {
}
