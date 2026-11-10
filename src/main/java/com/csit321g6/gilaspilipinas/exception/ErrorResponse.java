package com.csit321g6.gilaspilipinas.exception;

import java.time.LocalDateTime;
import java.util.Map;

/** One consistent JSON shape for every error the API returns. */
public record ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path,
                            Map<String, String> fieldErrors) {
}
