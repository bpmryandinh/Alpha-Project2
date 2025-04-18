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

/*
Now using the student b numbers from that csv (ex. B1008). I want you to generate 50 students using this format: B1001,C5012;C1024;C3012,John,Doe,Male,john.doe@example.com,3.75,"123 Main St Anytown CA 94102",MATH2004;ENGL1613,Sophomore
 */