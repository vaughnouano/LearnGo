package com.csit321section.groupname.repository;

import com.csit321section.groupname.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    // Derived query: WHERE course.course_id = ? ORDER BY order_no ASC
    List<Lesson> findByCourse_CourseIdOrderByOrderNoAsc(Long courseId);
}
