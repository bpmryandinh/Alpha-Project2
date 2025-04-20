package com.example.loginpage.Models;

public class Professor {
    private String professorID;
    private String[] Courses;
    private String fname;
    private String lname;
    private String gender;
    private String email;
    private String address;
    private String birthday;
    private String phone;

    public Professor(String professorID, String rawCourses, String fname, String lname, String gender, String email, String address, String birthday, String phone) {
        this.professorID = professorID;
        Courses = splitCourses(rawCourses);
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.phone = phone;
    }

    public String[] splitCourses(String coursesRaw) {
        return coursesRaw.split(";");
    }

}
