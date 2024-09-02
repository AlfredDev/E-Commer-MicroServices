package com.alfred.customer.Exceptions;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
