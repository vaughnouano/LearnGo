package com.csit321section.groupname.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/** What the client SENDS for a lesson. courseId is the foreign key to the parent course. */
public record LessonRequest(
        @NotNull(message = "courseId is required") Long courseId,
        @NotBlank(message = "title is required") @Size(max = 150, message = "title must be at most 150 characters") String title,
        String content,
        @NotNull(message = "orderNo is required") @Min(value = 1, message = "orderNo must be at least 1") Integer orderNo) {
}
