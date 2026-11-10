package com.csit321section.groupname.exception;

import java.time.LocalDateTime;
import java.util.Map;

/** One consistent JSON shape for every error the API returns. */
public record ErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path,
                            Map<String, String> fieldErrors) {
}
