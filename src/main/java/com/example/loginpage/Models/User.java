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
    private String year;
    private String CoursesString;


    /*
    * Professor and student options
    */

    public User(String userID, String coursesRaw, String fname, String lname, String gender, String email, String GPA, String address, String birthday, String phone, String year ) {
        this.userID = userID;
        this.Courses = splitCourses(coursesRaw);
        this.setCoursesString();
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.email = email;
        this.GPA = GPA;
        this.address = address;
        this.birthday = birthday;
        this.phone = phone;
        this.year = year;
    }

    public String[] splitCourses(String coursesRaw) {
        return coursesRaw.split(";");
    }

    public void setCoursesString() {
        String result = "";
        for (String course : Courses) {
            result += course + ", ";
        }
        this.CoursesString = result;
    }

    public String[] getCourses() {
        return Courses;
    }

    public String getCoursesString() {
        return CoursesString;
    }

    public String getUserID() {
        return userID;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getGPA() {
        return GPA;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getYear() {
        return year;
    }
}
