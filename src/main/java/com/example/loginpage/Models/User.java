package com.example.loginpage.Models;

public class User {
    private String userID;
    private String[] Courses;
    private String fname;
    private String lname;
    private String gender;
    private String email;
    private String GPA;
    private String address;
    private String birthday;
    private String phone;


    /*
    * Professor and student options
    */

    public User(String userID, String coursesRaw, String fname, String lname, String gender, String email, String GPA, String address, String birthday, String phone) {
        this.userID = userID;
        this.Courses = splitCourses(coursesRaw);
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.email = email;
        this.GPA = GPA;
        this.address = address;
        this.birthday = birthday;
        this.phone = phone;
    }

    public String[] splitCourses(String coursesRaw) {
        return coursesRaw.split(";");
    }

    public String[] getCourses() {
        return Courses;
    }
}
