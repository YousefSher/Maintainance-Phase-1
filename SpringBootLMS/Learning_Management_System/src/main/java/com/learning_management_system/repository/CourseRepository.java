package com.learning_management_system.repository;

import com.learning_management_system.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


//@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findByCourseName(String courseName);
    @Query("SELECT CASE WHEN COUNT(course) > 0 THEN true ELSE false END " +
            "FROM Course course " +
            "WHERE course.instructorId.userAccountId = :instructorId " +
            "AND course.courseId = :courseId")
    boolean findByInstructorId(int instructorId , int courseId);
}
