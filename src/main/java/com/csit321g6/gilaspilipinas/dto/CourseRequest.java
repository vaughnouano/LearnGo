package com.csit321g6.gilaspilipinas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** What the client SENDS when creating/updating a course. */
public record CourseRequest(
        @NotBlank(message = "code is required") @Size(max = 20, message = "code must be at most 20 characters") String code,
        @NotBlank(message = "title is required") @Size(max = 150, message = "title must be at most 150 characters") String title,
        String description) {
}
