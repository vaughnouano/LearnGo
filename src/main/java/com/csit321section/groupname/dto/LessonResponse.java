package com.csit321section.groupname.dto;

/** What the API RETURNS for a lesson. Only the parent's id/title are exposed, never the full Course object. */
public record LessonResponse(Long lessonId, Long courseId, String courseTitle, String title, String content, Integer orderNo) {
}
