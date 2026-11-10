package com.csit321section.groupname.service;

import com.csit321section.groupname.dto.CourseRequest;
import com.csit321section.groupname.dto.CourseResponse;
import java.util.List;

public interface CourseService {
    CourseResponse create(CourseRequest request);
    List<CourseResponse> getAll();
    CourseResponse getById(Long id);
    CourseResponse update(Long id, CourseRequest request);
    void delete(Long id);
}
