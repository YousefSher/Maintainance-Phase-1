package com.learning_management_system.service;
import com.learning_management_system.entity.Instructor;
import com.learning_management_system.entity.Users;
import com.learning_management_system.repository.InstructorRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InstructorService {
    private final InstructorRepository instructorRepository;
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }
    public Optional<Instructor> findById(int userId) {
        return instructorRepository.findById(userId);
    }

    public void save(int instructorId ,Instructor instructor , HttpServletRequest request) {
        Users loggedInInstructor = (Users) request.getSession().getAttribute("user");
        if (loggedInInstructor == null) {
            throw new IllegalArgumentException("User is not logged in.");
        }
        if(instructorId != loggedInInstructor.getUserId()) {
            throw new IllegalArgumentException("You are not authorized to perform this action.");
        }
        Instructor newInstructor = instructorRepository.getReferenceById(instructorId);
        newInstructor.setFirstName(instructor.getFirstName());
        newInstructor.setLastName(instructor.getLastName());
        instructorRepository.save(newInstructor);
    }



}
