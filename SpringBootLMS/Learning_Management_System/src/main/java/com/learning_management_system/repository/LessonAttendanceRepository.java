package com.learning_management_system.repository;

import com.learning_management_system.entity.Lesson;
import com.learning_management_system.entity.LessonAttendance;
import com.learning_management_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonAttendanceRepository extends JpaRepository<LessonAttendance, Integer> {
    boolean existsByLessonIdAndStudentId(Lesson lessonId , Student studentId);
    List <LessonAttendance> findAllByLessonId (Lesson lessonId);
}
