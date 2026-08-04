package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        student.setId(IdGenerator.getNextStudentId());
        studentRepository.addStudent(student);
    }

    public List<Student> listStudents() {
        return studentRepository.getAllStudents();
    }

    public Student getStudentById(int id) {

        Student student = studentRepository.getStudentById(id);

        if (student == null) {
            throw new EntityNotFoundException("Student not found with ID: " + id);
        }

        return student;
    }

    public boolean deactivateStudent(int id) {
        return studentRepository.deactivateStudent(id);
    }

    public boolean updateStudent(Student updatedStudent) {
        return studentRepository.updateStudent(updatedStudent);
    }


}
