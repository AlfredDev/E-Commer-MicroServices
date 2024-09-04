package com.alfred.order.Models.OrderLine;

import com.alfred.order.DTO.OrdersLineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;

    public Integer saveOrderLine(OderLineRequest oderLineRequest) {
        var order = mapper.toOrderLine(oderLineRequest);
        return orderLineRepository.save(order).getId();
    }

    public List<OrdersLineResponse> findByOrderId(Integer orderId) {
        return orderLineRepository.findAllOrderId(orderId).stream()
                .map(mapper::toOrdersLineResponse)
                .collect(Collectors.toList());
    }
}
