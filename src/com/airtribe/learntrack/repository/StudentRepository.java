package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public boolean deactivateStudent(int id) {
        Student student = getStudentById(id);

        if (student != null) {
            student.setActive(false);
            return true;
        }

        return false;
    }

    public boolean updateStudent(Student updatedStudent) {
        Student existingStudent = getStudentById(updatedStudent.getId());
        if (existingStudent != null) {
            existingStudent.setFirstName(updatedStudent.getFirstName());
            existingStudent.setLastName(updatedStudent.getLastName());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setBatch(updatedStudent.getBatch());
            existingStudent.setActive(updatedStudent.isActive());

            return true;
        }

        return false;
    }
}
