package com.csit321g6.gilaspilipinas.controller;

import com.csit321g6.gilaspilipinas.dto.CourseRequest;
import com.csit321g6.gilaspilipinas.dto.CourseResponse;
import com.csit321g6.gilaspilipinas.dto.LessonResponse;
import com.csit321g6.gilaspilipinas.service.CourseService;
import com.csit321g6.gilaspilipinas.service.LessonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** REST endpoints for Course. The controller only handles HTTP; all logic is in the service. */
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;
    private final LessonService lessonService;

    public CourseController(CourseService courseService, LessonService lessonService) {
        this.courseService = courseService;
        this.lessonService = lessonService;
    }

    @PostMapping
    public ResponseEntity<CourseResponse> create(@Valid @RequestBody CourseRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(request));
    }

    @GetMapping
    public List<CourseResponse> getAll() {
        return courseService.getAll();
    }

    @GetMapping("/{id}")
    public CourseResponse getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    // Relationship endpoint: all lessons that belong to one course, in order.
    @GetMapping("/{id}/lessons")
    public List<LessonResponse> getLessonsOfCourse(@PathVariable Long id) {
        return lessonService.getByCourse(id);
    }

    @PutMapping("/{id}")
    public CourseResponse update(@PathVariable Long id, @Valid @RequestBody CourseRequest request) {
        return courseService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
