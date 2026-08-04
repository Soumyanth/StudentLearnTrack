package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses);
    }

    public Course getCourseById(int id) {
        for (Course course : courses) {
            if (course.getId() == id) {
                return course;
            }
        }
        return null;
    }

    public boolean deactivateCourse(int id) {
        Course course = getCourseById(id);
        if (course != null) {
            course.setActive(false);
            return true;
        }
        return false;
    }

    public boolean updateCourse(Course updatedCourse) {
        Course existingCourse = getCourseById(updatedCourse.getId());
        if (existingCourse != null) {
            existingCourse.setCourseName(updatedCourse.getCourseName());
            existingCourse.setDescription(updatedCourse.getDescription());
            existingCourse.setDurationInWeeks(updatedCourse.getDurationInWeeks());
            existingCourse.setActive(updatedCourse.isActive());
            return true;

        }
        return false;
    }
}
