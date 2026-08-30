package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void addCourse(Course course) {
        course.setId(IdGenerator.getNextCourseId());
        courseRepository.addCourse(course);
    }

    public List<Course> listCourses() {
        return courseRepository.getAllCourses();
    }

    public Course getCourseById(int id) {
        Course course = courseRepository.getCourseById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course not found with ID: " + id);
        }
        return course;
    }

    public boolean deactivateCourse(int id) {
        return courseRepository.deactivateCourse(id);
    }

    public boolean updateCourse(Course updatedCourse) {
        return courseRepository.updateCourse(updatedCourse);
    }
}
