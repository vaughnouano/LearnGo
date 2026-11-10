package com.csit321g6.gilaspilipinas.service;

import com.csit321g6.gilaspilipinas.dto.CourseRequest;
import com.csit321g6.gilaspilipinas.dto.CourseResponse;
import java.util.List;

public interface CourseService {
    CourseResponse create(CourseRequest request);
    List<CourseResponse> getAll();
    CourseResponse getById(Long id);
    CourseResponse update(Long id, CourseRequest request);
    void delete(Long id);
}
