package com.csit321section.groupname.controller;

import com.csit321section.groupname.dto.LessonRequest;
import com.csit321section.groupname.dto.LessonResponse;
import com.csit321section.groupname.service.LessonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/** REST endpoints for Lesson. The parent course is referenced by courseId in the request body. */
@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping
    public ResponseEntity<LessonResponse> create(@Valid @RequestBody LessonRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.create(request));
    }

    @GetMapping
    public List<LessonResponse> getAll() {
        return lessonService.getAll();
    }

    @GetMapping("/{id}")
    public LessonResponse getById(@PathVariable Long id) {
        return lessonService.getById(id);
    }

    @PutMapping("/{id}")
    public LessonResponse update(@PathVariable Long id, @Valid @RequestBody LessonRequest request) {
        return lessonService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
