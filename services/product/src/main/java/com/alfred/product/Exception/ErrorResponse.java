package com.alfred.product.Exception;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
