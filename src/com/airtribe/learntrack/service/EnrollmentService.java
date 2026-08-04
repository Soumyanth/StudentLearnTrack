package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class EnrollmentService {

    private EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollment.setId(IdGenerator.getNextEnrollmentId());
        enrollmentRepository.addEnrollment(enrollment);
    }

    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
        return enrollmentRepository.getEnrollmentsByStudentId(studentId);
    }

    public List<Enrollment> listEnrollments() {
        return enrollmentRepository.getAllEnrollments();
    }

    public Enrollment getEnrollmentById(int id) {
        Enrollment enrollment =  enrollmentRepository.getEnrollmentById(id);
        if (enrollment == null) {
            throw new EntityNotFoundException("Enrollment not found with ID: " + id);
        }
        return enrollment;
    }

    public boolean updateEnrollment(Enrollment updatedEnrollment) {
        return enrollmentRepository.updateEnrollment(updatedEnrollment);
    }
}
