package com.airtribe.learntrack.ui;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static int choice = 0;
    private static StudentService studentService;
    private static CourseService courseService;
    private static EnrollmentService enrollmentService;

    public static void main(String[] args) {

        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        studentService = new StudentService(studentRepository);
        courseService = new CourseService(courseRepository);
        enrollmentService = new EnrollmentService(enrollmentRepository);

        do {
            displayLearnTrack();
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case MenuOptions.STUDENT_MANAGEMENT:
                        studentManagement();
                        break;

                    case MenuOptions.COURSE_MANAGEMENT:
                        courseManagement();
                        break;

                    case MenuOptions.ENROLLMENT_MANAGEMENT:
                        enrollmentManagement();
                        break;

                    case MenuOptions.BACK:
                        System.out.println("Thank you for using LearnTrack.");
                        break;

                    default:
                        System.out.println(AppConstants.INVALID_OPTION);
                }

            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            }
        } while (choice != 0);


        scanner.close();
    }

    public static void displayLearnTrack() {
        System.out.println("========== LearnTrack ==========");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("0. Exit");
        System.out.println("================================");

    }

    public static void studentManagement() {
        System.out.println("========== Student Management ==========");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Update Student");
        System.out.println("5. Deactivate Student");
        System.out.println("0. Back");
        System.out.println("================================");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case MenuOptions.ADD:
                addStudent();
                break;
            case MenuOptions.VIEW:
                getAllStudents();
                break;
            case MenuOptions.SEARCH:
                getStudentById();
                break;
            case MenuOptions.UPDATE:
                updateStudent();
                break;
            case MenuOptions.DEACTIVATE:
                deactivateStudent();
                break;
            case MenuOptions.BACK:
                return;
            default:
                System.out.println(AppConstants.INVALID_OPTION);
        }

    }

    public static void addStudent() {
        System.out.print("Enter FirstName : ");
        String firstName = scanner.nextLine();
        System.out.print("Enter LastName : ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter batch : ");
        String batch = scanner.nextLine();

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        student.setBatch(batch);
        student.setActive(true);
        studentService.addStudent(student);
        System.out.println(AppConstants.STUDENT_ADDED);
    }

    public static void getAllStudents() {
        List<Student> students = studentService.listStudents();
        if (students.isEmpty()) {
            System.out.println(AppConstants.STUDENT_NOT_FOUND);
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }

    }

    public static void getStudentById() {
        System.out.print("Enter Student Id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Student student = studentService.getStudentById(id);
            System.out.println(student);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateStudent() {
        System.out.print("Enter Student Id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Student existingStudnet = null;
        try {
            existingStudnet = studentService.getStudentById(id);
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return;
        }
        System.out.print("Enter FirstName : ");
        String firstName = scanner.nextLine();
        System.out.print("Enter LastName : ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter batch : ");
        String batch = scanner.nextLine();

        Student updateStudent = new Student();
        updateStudent.setId(id);
        updateStudent.setFirstName(firstName);
        updateStudent.setLastName(lastName);
        updateStudent.setEmail(email);
        updateStudent.setBatch(batch);
        updateStudent.setActive(existingStudnet.isActive());

        boolean status = studentService.updateStudent(updateStudent);

        if (status) {
            System.out.println(AppConstants.STUDENT_UPDATED);
        } else {
            System.out.println("unable to update student");
        }

    }

    public static void deactivateStudent() {
        System.out.print("Enter Student Id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Student student = studentService.getStudentById(id);
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return;
        }
        boolean status = studentService.deactivateStudent(id);
        if (status) {
            System.out.println(AppConstants.STUDENT_DEACTIVATED);
        } else {
            System.out.println("Unable to deactivate student");
        }
    }

    public static void courseManagement() {
        System.out.println("========== Course Management ==========");
        System.out.println("1. Add Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Search Course by Id");
        System.out.println("4. Update Course");
        System.out.println("5. Deactivate Course");
        System.out.println("0. Back");
        System.out.println("================================");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case MenuOptions.ADD:
                addCourse();
                break;
            case MenuOptions.VIEW:
                getAllCourses();
                break;
            case MenuOptions.SEARCH:
                getCourseById();
                break;
            case MenuOptions.UPDATE:
                updateCourse();
                break;
            case MenuOptions.DEACTIVATE:
                deactivateCourse();
                break;
            case MenuOptions.BACK:
                return;
            default:
                System.out.println(AppConstants.INVALID_OPTION);
        }

    }

    public static void addCourse() {
        System.out.print("Enter Course : ");
        String courseName = scanner.nextLine();
        System.out.print("Enter Description : ");
        String description = scanner.nextLine();
        System.out.print("Enter Duration in Weeks: ");
        int durationInWeeks = scanner.nextInt();
        scanner.nextLine();

        if (durationInWeeks <= 0) {
            System.out.println(AppConstants.DURATION_ERROR);
            return;
        }

        Course course = new Course();
        course.setCourseName(courseName);
        course.setDescription(description);
        course.setDurationInWeeks(durationInWeeks);
        course.setActive(true);

        courseService.addCourse(course);
        System.out.println(AppConstants.COURSE_ADDED);
    }

    public static void getAllCourses() {

        List<Course> courseList = courseService.listCourses();
        if (courseList.isEmpty()) {
            System.out.println(AppConstants.COURSE_NOT_FOUND);
            return;
        }
        for (Course course : courseList) {
            System.out.println(course);
        }
    }

    public static void getCourseById() {
        System.out.print("Enter the Course Id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Course course = courseService.getCourseById(id);
            System.out.println(course);
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return;
        }
    }

    public static void updateCourse() {
        System.out.print("Enter Course Id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Course existingCourse = null;
        try {
            existingCourse = courseService.getCourseById(id);
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return;
        }

        System.out.print("Enter Course : ");
        String courseName = scanner.nextLine();
        System.out.print("Enter Description : ");
        String description = scanner.nextLine();
        System.out.print("Enter Duration in Weeks: ");
        int durationInWeeks = scanner.nextInt();
        scanner.nextLine();
        if (durationInWeeks <= 0) {
            System.out.println(AppConstants.DURATION_ERROR);
            return;
        }
        Course updatedCourse = new Course();
        updatedCourse.setId(id);
        updatedCourse.setCourseName(courseName);
        updatedCourse.setDescription(description);
        updatedCourse.setDurationInWeeks(durationInWeeks);
        updatedCourse.setActive(existingCourse.isActive());

        boolean status = courseService.updateCourse(updatedCourse);
        if (status) {
            System.out.println(AppConstants.COURSE_UPDATED);
        } else {
            System.out.println("Unable to update course");
        }
    }

    public static void deactivateCourse() {
        System.out.println("Enter the Course Id : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Course course = courseService.getCourseById(id);
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return;
        }
        boolean status = courseService.deactivateCourse(id);
        if (status) {
            System.out.println(AppConstants.COURSE_DEACTIVATED);
        } else {
            System.out.println("Unable to deactivate course");
        }
    }

    public static void enrollmentManagement() {
        System.out.println("========== Enrollment Management ==========");
        System.out.println("1. Enroll a student in course");
        System.out.println("2. View enrollments for a student");
        System.out.println("3. Mark enrollment as completed");
        System.out.println("4. Mark enrollment as cancelled");
        System.out.println("0. Back");
        System.out.println("================================");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case MenuOptions.ENROLL_STUDENT_IN_A_CLASS:
                enrollStudentInCourse();
                break;
            case MenuOptions.VIEW_ENROLLMENT_FOR_A_STUDENT:
                viewEnrollmentsForAStudent();
                break;
            case MenuOptions.MARK_ENROLLMENT_AS_COMPLETED:
                updateEnrollmentStatus(EnrollmentStatus.COMPLETED);
                break;
            case MenuOptions.MARK_ENROLLMENT_AS_CANCELLED:
                updateEnrollmentStatus(EnrollmentStatus.CANCELLED);
                break;
            case MenuOptions.BACK:
                return;
            default:
                System.out.println(AppConstants.INVALID_OPTION);
        }
    }

    public static void enrollStudentInCourse() {
        System.out.print("Enter the Student Id:");
        int studentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter the Course Id:");
        int courseId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter the Enrollment Date: ");
        String enrollmentDate = scanner.nextLine();
        Student student;
        try {
            student = studentService.getStudentById(studentId);
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return;
        }
        if (!student.isActive()) {
            System.out.println("Student is not active to enroll");
            return;
        }
        Course course;
        try {
            course = courseService.getCourseById(courseId);
        } catch (EntityNotFoundException exception) {
            System.out.println(exception.getMessage());
            return;
        }
        if (!course.isActive()) {
            System.out.println("Course is not active to enroll");
            return;
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(studentId);
        enrollment.setCourseId(courseId);
        enrollment.setEnrollmentDate(enrollmentDate);
        enrollment.setStatus(EnrollmentStatus.ACTIVE);

        enrollmentService.addEnrollment(enrollment);
        System.out.println(AppConstants.ENROLLMENT_ADDED);

    }

    public static void viewEnrollmentsForAStudent() {
        System.out.print("Enter Student Id: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        try {
            studentService.getStudentById(studentId);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        List<Enrollment> enrollments =
                enrollmentService.getEnrollmentsByStudentId(studentId);

        if (enrollments.isEmpty()) {
            System.out.println(AppConstants.NO_ENROLLMENTS_FOR_STUDENT);
            return;
        }

        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }

    }

    public static void updateEnrollmentStatus(EnrollmentStatus status) {

        System.out.print("Enter Enrollment Id: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Enrollment existingEnrollment;

        try {
            existingEnrollment = enrollmentService.getEnrollmentById(id);
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        Enrollment updatedEnrollment = new Enrollment();

        updatedEnrollment.setId(existingEnrollment.getId());
        updatedEnrollment.setStudentId(existingEnrollment.getStudentId());
        updatedEnrollment.setCourseId(existingEnrollment.getCourseId());
        updatedEnrollment.setEnrollmentDate(existingEnrollment.getEnrollmentDate());
        updatedEnrollment.setStatus(status);

        boolean result = enrollmentService.updateEnrollment(updatedEnrollment);

        if (result) {
            if (status == EnrollmentStatus.COMPLETED) {
                System.out.println(AppConstants.ENROLLMENT_COMPLETED);
            } else {
                System.out.println(AppConstants.ENROLLMENT_CANCELLED);
            }
        } else {
            System.out.println("Unable to update enrollment.");
        }
    }

}
