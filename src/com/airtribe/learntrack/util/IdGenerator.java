package com.airtribe.learntrack.util;

public class IdGenerator {

    private static int studentIdCounter = 1001;
    private static int courseIdCounter = 501;
    private static int enrollmentIdCounter = 1;

    private IdGenerator(){
        // Prevent object creation from other class
    }
    public static int getNextStudentId(){
        return studentIdCounter++;
    }
    public static int  getNextCourseId(){
        return courseIdCounter++;
    }
    public static int getNextEnrollmentId(){
        return enrollmentIdCounter++;
    }
}
