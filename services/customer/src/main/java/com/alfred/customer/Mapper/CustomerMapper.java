package com.alfred.customer.Mapper;

import com.alfred.customer.customer.Customer;
import com.alfred.customer.DTO.CustomerRequest;
import com.alfred.customer.DTO.CustomerResponse;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest customerRequest) {
        if (customerRequest == null) {
            return null;
        }
        return Customer.builder()
                .id(customerRequest.id())
                .email(customerRequest.email())
                .address(customerRequest.address())
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getLastName(),
                customer.getFirstName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
