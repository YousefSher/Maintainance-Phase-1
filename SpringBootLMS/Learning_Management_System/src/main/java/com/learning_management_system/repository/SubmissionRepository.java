package com.learning_management_system.repository;

import com.learning_management_system.entity.Assignment;
import com.learning_management_system.entity.Student;
import com.learning_management_system.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    List<Submission> findByStudentId(Student student);
    List <Submission> findAllByAssignmentId (Assignment assignmentId);
}
