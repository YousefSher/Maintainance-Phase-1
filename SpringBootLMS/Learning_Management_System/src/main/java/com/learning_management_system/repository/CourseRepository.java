package com.learning_management_system.repository;

import com.learning_management_system.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


//@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findByCourseName(String courseName);
    @Query("SELECT c FROM Course c WHERE LOWER(c.courseName) LIKE LOWER(CONCAT('%', :namePart, '%'))")
    List<Course> findByCourseNameContaining(@Param("namePart") String namePart);
    @Query("SELECT CASE WHEN COUNT(course) > 0 THEN true ELSE false END " +
            "FROM Course course " +
            "WHERE course.instructorId.userAccountId = :instructorId " +
            "AND course.courseId = :courseId")
    boolean findByInstructorId(int instructorId , int courseId);
}
