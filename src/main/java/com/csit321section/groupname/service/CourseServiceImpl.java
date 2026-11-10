package com.csit321section.groupname.service;

import com.csit321section.groupname.dto.CourseRequest;
import com.csit321section.groupname.dto.CourseResponse;
import com.csit321section.groupname.entity.Course;
import com.csit321section.groupname.exception.DuplicateResourceException;
import com.csit321section.groupname.exception.ResourceNotFoundException;
import com.csit321section.groupname.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public CourseResponse create(CourseRequest request) {
        log.debug("Creating course with code={}", request.code());
        if (courseRepository.existsByCode(request.code().trim())) {
            throw new DuplicateResourceException("Course code '" + request.code() + "' already exists");
        }
        Course course = new Course();
        apply(course, request);
        Course saved = courseRepository.save(course);
        log.info("Created course id={} code={}", saved.getCourseId(), saved.getCode());
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponse> getAll() {
        log.debug("Fetching all courses");
        return courseRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponse getById(Long id) {
        log.debug("Fetching course id={}", id);
        return toResponse(findOrThrow(id));
    }

    @Override
    @Transactional
    public CourseResponse update(Long id, CourseRequest request) {
        log.debug("Updating course id={}", id);
        Course course = findOrThrow(id);
        // Only a conflict if ANOTHER course already uses this code.
        courseRepository.findByCode(request.code().trim()).ifPresent(other -> {
            if (!other.getCourseId().equals(id)) {
                throw new DuplicateResourceException("Course code '" + request.code() + "' already exists");
            }
        });
        apply(course, request);
        Course saved = courseRepository.save(course);
        log.info("Updated course id={}", saved.getCourseId());
        return toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Deleting course id={}", id);
        Course course = findOrThrow(id);
        courseRepository.delete(course); // cascades to its lessons
        log.info("Deleted course id={} (and its lessons)", id);
    }

    private Course findOrThrow(Long id) {
        return courseRepository.findById(id).orElseThrow(() -> {
            log.warn("Course id={} not found", id);
            return new ResourceNotFoundException("Course with id " + id + " not found");
        });
    }

    private void apply(Course course, CourseRequest r) {
        course.setCode(r.code().trim());
        course.setTitle(r.title().trim());
        course.setDescription(r.description());
    }

    private CourseResponse toResponse(Course c) {
        return new CourseResponse(c.getCourseId(), c.getCode(), c.getTitle(), c.getDescription(), c.getLessons().size());
    }
}
