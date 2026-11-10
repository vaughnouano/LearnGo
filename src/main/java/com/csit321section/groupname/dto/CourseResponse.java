package com.csit321section.groupname.dto;

/** What the API RETURNS for a course. lessonCount proves the one-to-many relationship works. */
public record CourseResponse(Long courseId, String code, String title, String description, int lessonCount) {
}
