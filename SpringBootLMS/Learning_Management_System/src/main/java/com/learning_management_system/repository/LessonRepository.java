package com.learning_management_system.repository;


import com.learning_management_system.entity.Course;
import com.learning_management_system.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    List<Lesson> findByCourseId(Course course);
}
