package com.csit321g6.gilaspilipinas.service;

import com.csit321g6.gilaspilipinas.dto.LessonRequest;
import com.csit321g6.gilaspilipinas.dto.LessonResponse;
import java.util.List;

public interface LessonService {
    LessonResponse create(LessonRequest request);
    List<LessonResponse> getAll();
    List<LessonResponse> getByCourse(Long courseId);
    LessonResponse getById(Long id);
    LessonResponse update(Long id, LessonRequest request);
    void delete(Long id);
}
