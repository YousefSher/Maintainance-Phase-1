package com.learning_management_system.service;

import com.learning_management_system.entity.Student;
import com.learning_management_system.entity.Users;
import com.learning_management_system.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Optional<Student> findById(int userId) {
        return studentRepository.findById(userId);
    }

    public void save(int studentId ,Student student , HttpServletRequest request) {
        Users loggedInInstructor = (Users) request.getSession().getAttribute("user");
        if (loggedInInstructor == null) {
            throw new IllegalArgumentException("User is not logged in.");
        }
        if(studentId != loggedInInstructor.getUserId()) {
            throw new IllegalArgumentException("You are not authorized to perform this action.");
        }
        Student newStudent = studentRepository.getReferenceById(studentId);
        newStudent.setFirstName(student.getFirstName());
        newStudent.setLastName(student.getLastName());
        studentRepository.save(newStudent);
    }
}
