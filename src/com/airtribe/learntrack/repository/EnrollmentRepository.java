package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {

    private List<Enrollment> enrollments = new ArrayList<>();

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return new ArrayList<>(enrollments);
    }

    public List<Enrollment> getEnrollmentsByStudentId(int studentId) {

        List<Enrollment> studentEnrollments = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getStudentId() == studentId) {
                studentEnrollments.add(enrollment);
            }
        }

        return studentEnrollments;
    }

    public Enrollment getEnrollmentById(int id) {
        for (Enrollment enrollment : enrollments) {
            if (enrollment.getId() == id) {
                return enrollment;
            }
        }
        return null;
    }

    public boolean updateEnrollment(Enrollment updatedEnrollment) {
        Enrollment existingEnrollment = getEnrollmentById(updatedEnrollment.getId());
        if (existingEnrollment != null) {
            existingEnrollment.setCourseId(updatedEnrollment.getCourseId());
            existingEnrollment.setStudentId(updatedEnrollment.getStudentId());
            existingEnrollment.setEnrollmentDate(updatedEnrollment.getEnrollmentDate());
            existingEnrollment.setStatus(updatedEnrollment.getStatus());

            return true;

        }
        return false;
    }

}
