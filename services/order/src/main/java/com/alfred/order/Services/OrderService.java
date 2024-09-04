package com.alfred.order.Services;

import com.alfred.order.DTO.OrderRequest;
import com.alfred.order.DTO.OrderResponse;
import com.alfred.order.DTO.PurchaseRequest;
import com.alfred.order.Exceptions.BusinessException;
import com.alfred.order.Models.OrderLine.OderLineRequest;
import com.alfred.order.Models.OrderLine.OrderLineService;
import com.alfred.order.Models.OrderMapper;
import com.alfred.order.Models.product.ProductClient;
import com.alfred.order.Models.customer.CustomerClient;
import com.alfred.order.kafka.OrderConfirmation;
import com.alfred.order.kafka.OrderProducer;
import com.alfred.order.payment.PaymentClient;
import com.alfred.order.payment.PaymentRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    private  final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {
        //check the customer --> OpengFeing
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Customer not found, cannot create order"));
        // purchase the products --> product-ms (ResTemplate)
        var purchaseProducts = this.productClient.purchaseProducts(request.products());
        // persist the order

        var order = this.orderRepository.save(mapper.toOrder(request));

        // persist the order lines

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // todo start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        // send the order confirmation --> notification-ms (Kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchaseProducts
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format(
                        "Not order found with the provided id: %d", orderId
                )));
    }
}
