package com.example.loginpage.Services;

import com.example.loginpage.Models.Course;
import com.example.loginpage.Models.User;

import java.util.HashMap;

public class HashService {
    // Key:Value Pairs
    //
    private HashMap<Integer, Course> Courses;
    private HashMap<Integer, User> Users;
    private HashMap<Integer, String[]> CourseData;

    public void test(){
        // Takes in an ID returns the course associated to that ID
        Courses.get("B0012155");
    }

    // Student Session: Sending the user course Data
    public static Course[] findCourses(String[] CourseID) {
        Course[] courses = new Course[CourseID.length];
        return courses;
    }

    // Professor Session: Sending UserData for a course
    public static String[] findStudents(int[] UserID) {
    String[] students = new String[UserID.length];
    return students;
    }

}
