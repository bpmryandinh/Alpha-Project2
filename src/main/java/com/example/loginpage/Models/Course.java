package com.example.loginpage.Models;

public class Course {
    String courseID;
    String courseName;
    String[] userIDs;
    String courseProfessor;
    String[] courseData;

    public Course(String courseID, String courseName, String usersRaw, String courseProfessor, String[] courseData) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.userIDs = splitUsers(usersRaw);
        this.courseProfessor = courseProfessor;
        this.courseData = courseData;
    }

    public String[] splitUsers(String usersRaw) {
        return usersRaw.split(";");
    }

    public String getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public String[] getUserIDs() {
        return userIDs;
    }
    
    public String getCourseProfessor() {
        return courseProfessor;
    }

    public String[] getCourseData() {
        return courseData;
    }
}
