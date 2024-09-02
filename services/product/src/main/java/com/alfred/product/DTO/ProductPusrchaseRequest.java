package com.alfred.product.DTO;

import jakarta.validation.constraints.NotNull;

public record ProductPusrchaseRequest(
        @NotNull(message = "Product is mandatory")
        Integer productId,
        @NotNull(message = "Quantity is mandatory")
        double quantity
) {
}
