package com.example.loginpage.Models;

import javafx.scene.control.CheckBox;

public class Course {
    private String courseID;
    private String courseName;
    private String[] userIDs;
    private Professor courseProfessor;
    private String[] courseData;
    private String courseClassify;
    private CheckBox select;

    public Course(String courseID, String courseName, String usersRaw, String[] courseData, Professor courseProfessor) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.userIDs = splitUsers(usersRaw);
        this.courseData = courseData;
        this.courseClassify = courseData[2];
        this.courseProfessor = courseProfessor;
        this.select = new CheckBox();
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
    
    public Professor getCourseProfessor() {
        return courseProfessor;
    }

    public String[] getCourseData() {
        return courseData;
    }

    public String getCourseClassify() {
        return courseClassify;
    }

    public CheckBox getSelect() {
        return select;
    }

    public void setSelect(CheckBox select) {
        this.select = select;
    }
}

/*
Now using the student b numbers from that csv (ex. B1008). I want you to generate 50 students using this format: B1001,C5012;C1024;C3012,John,Doe,Male,john.doe@example.com,3.75,"123 Main St Anytown CA 94102",MATH2004;ENGL1613,Sophomore
 */