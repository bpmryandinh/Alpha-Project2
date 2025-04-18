package com.example.loginpage.Services;

import com.example.loginpage.Models.Course;
import com.example.loginpage.Models.User;

import java.util.HashMap;

public class HashService {
    /*
    * 2 separate methods to fill in data for course and user hashmaps
    * Integer = ID of course or user (depending on method)
    * */

    // Key:Value Pairs
    //
    private static HashMap<Integer, Course> Courses;
    private static HashMap<Integer, User> Users;
    private static HashMap<Integer, String[]> CourseData;

    public void test(){
        // Takes in an ID returns the course associated to that ID
        Courses.get("B0012155");
    }

    // Student Session: Sending the user course Data
    public static Course[] findCourses(String[] CourseID) {
        Course[] CourseReturn = new Course[CourseID.length];
        for(int i = 0; i >= CourseID.length; i++){
            CourseReturn[i] = Courses.get(CourseID[i]);
        }
        return CourseReturn;
    }

    // Professor Session: Sending UserData for a course
    public static User[] findStudents(int[] UserID) {
        User[] UserReturn = new User[UserID.length];
        for(int i = 0; i >= UserID.length; i++){
            UserReturn[i] = Users.get(UserID[i]);
        }
        return UserReturn;
    }

    // Method to fill in data for course hashmap
    public static void writeCourseHashMap(String[][] CourseSData) {
        for(String[] courseData : CourseSData){
            Course course = new Course(courseData[0], courseData[1], courseData[2], courseData[3]);
            Courses.put(Integer.parseInt(courseData[0]), course);
        }
    }

    // Method to fill in data for user hashmap
    // does the new String[]{userData[1]} work?
    public static void writeUserHashMap(String[][] UserSData){
        for(String[] userData : UserSData){
            User user = new User(userData[0], userData[1], userData[2], userData[3], userData[4], userData[5], userData[6], userData[7], userData[8],userData[9]);
            Users.put(userData[0], user);
        }
    }
}
