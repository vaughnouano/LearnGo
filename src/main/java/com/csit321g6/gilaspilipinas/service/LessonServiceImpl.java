package com.csit321g6.gilaspilipinas.service;

import com.csit321g6.gilaspilipinas.dto.LessonRequest;
import com.csit321g6.gilaspilipinas.dto.LessonResponse;
import com.csit321g6.gilaspilipinas.entity.Course;
import com.csit321g6.gilaspilipinas.entity.Lesson;
import com.csit321g6.gilaspilipinas.exception.ResourceNotFoundException;
import com.csit321g6.gilaspilipinas.repository.CourseRepository;
import com.csit321g6.gilaspilipinas.repository.LessonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    private static final Logger log = LoggerFactory.getLogger(LessonServiceImpl.class);

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public LessonServiceImpl(LessonRepository lessonRepository, CourseRepository courseRepository) {
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional
    public LessonResponse create(LessonRequest request) {
        log.debug("Creating lesson '{}' for courseId={}", request.title(), request.courseId());
        Course course = findCourseOrThrow(request.courseId()); // parent must exist first
        Lesson lesson = new Lesson();
        apply(lesson, request, course);
        Lesson saved = lessonRepository.save(lesson);
        log.info("Created lesson id={} in course id={}", saved.getLessonId(), course.getCourseId());
        return toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonResponse> getAll() {
        log.debug("Fetching all lessons");
        return lessonRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LessonResponse> getByCourse(Long courseId) {
        log.debug("Fetching lessons of course id={}", courseId);
        findCourseOrThrow(courseId);
        return lessonRepository.findByCourse_CourseIdOrderByOrderNoAsc(courseId).stream().map(this::toResponse).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public LessonResponse getById(Long id) {
        log.debug("Fetching lesson id={}", id);
        return toResponse(findLessonOrThrow(id));
    }

    @Override
    @Transactional
    public LessonResponse update(Long id, LessonRequest request) {
        log.debug("Updating lesson id={}", id);
        Lesson lesson = findLessonOrThrow(id);
        Course course = findCourseOrThrow(request.courseId());
        apply(lesson, request, course);
        Lesson saved = lessonRepository.save(lesson);
        log.info("Updated lesson id={}", saved.getLessonId());
        return toResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Deleting lesson id={}", id);
        Lesson lesson = findLessonOrThrow(id);
        lessonRepository.delete(lesson);
        log.info("Deleted lesson id={}", id);
    }

    private Lesson findLessonOrThrow(Long id) {
        return lessonRepository.findById(id).orElseThrow(() -> {
            log.warn("Lesson id={} not found", id);
            return new ResourceNotFoundException("Lesson with id " + id + " not found");
        });
    }

    private Course findCourseOrThrow(Long courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> {
            log.warn("Course id={} not found", courseId);
            return new ResourceNotFoundException("Course with id " + courseId + " not found - create the course first");
        });
    }

    private void apply(Lesson lesson, LessonRequest r, Course course) {
        lesson.setCourse(course);
        lesson.setTitle(r.title().trim());
        lesson.setContent(r.content());
        lesson.setOrderNo(r.orderNo());
    }

    private LessonResponse toResponse(Lesson l) {
        return new LessonResponse(l.getLessonId(), l.getCourse().getCourseId(), l.getCourse().getTitle(),
                l.getTitle(), l.getContent(), l.getOrderNo());
    }
}
