package com.csit321section.groupname.service;

import com.csit321section.groupname.dto.LessonRequest;
import com.csit321section.groupname.dto.LessonResponse;
import java.util.List;

public interface LessonService {
    LessonResponse create(LessonRequest request);
    List<LessonResponse> getAll();
    List<LessonResponse> getByCourse(Long courseId);
    LessonResponse getById(Long id);
    LessonResponse update(Long id, LessonRequest request);
    void delete(Long id);
}
