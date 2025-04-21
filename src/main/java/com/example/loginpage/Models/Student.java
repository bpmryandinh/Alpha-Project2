package com.example.loginpage.Models;

public class Student extends User {
    private String GPA;
    private String year;
    private String CoursesString;


    /*
    * Professor and student options
    */

    public Student(String userID, String coursesRaw, String fname, String lname, String gender, String email, String GPA, String address, String birthday, String phone, String year ) {
        super(userID, coursesRaw, fname, lname, gender, email, address, birthday, phone);
        this.setCoursesString();
        this.GPA = GPA;
        this.year = year;
    }

    public void setCoursesString() {
        String result = "";
        for (String course : getCourses()) {
            result += course + ", ";
        }
        this.CoursesString = result;
    }

    public String getGPA() {
        return GPA;
    }

    public String getYear() {
        return year;
    }

    public String getCoursesString() {
        return CoursesString;
    }
}
