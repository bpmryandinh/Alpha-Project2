package com.example.loginpage.Models;

public class Course {
    String courseID;
    String courseName;
    String userIDs;
    String courseProfessor;

    public Course(String courseID, String courseName, String courseData, String courseProfessor) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.userIDs = courseData;
        this.courseProfessor = courseProfessor;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUserIDs() {
        return userIDs;
    }

    public void setUserIDs(String userIDs) {
        this.userIDs = userIDs;
    }

    public String getCourseProfessor() {
        return courseProfessor;
    }

    public void setCourseProfessor(String courseProfessor) {
        this.courseProfessor = courseProfessor;
    }


}
