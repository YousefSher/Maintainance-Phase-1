package com.learning_management_system.repository;

import com.learning_management_system.entity.Course;
import com.learning_management_system.entity.Enrollment;
import com.learning_management_system.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    boolean existsByStudentAndCourse(Student student, Course course);
    List<Enrollment> findByCourse(Course course);
    Enrollment findByStudentAndCourse(Student student, Course course);
    @Query("SELECT e.course FROM Enrollment e WHERE e.student.userAccountId = :userAccountId")
    List<Course> findStudentEnrollments(@Param("userAccountId") int userAccountId);
}
