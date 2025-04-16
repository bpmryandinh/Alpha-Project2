package com.example.loginpage.Models;

public class User {
    private String userID;
    private String Courses;
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

    public User(String userID, String courses, String fname, String lname, String gender, String email, String GPA, String address, String birthday, String phone) {
        this.userID = userID;
        Courses = courses;
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.email = email;
        this.GPA = GPA;
        this.address = address;
        this.birthday = birthday;
        this.phone = phone;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCourses() {
        return Courses;
    }

    public void setCourses(String courses) {
        Courses = courses;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGPA() {
        return GPA;
    }

    public void setGPA(String GPA) {
        this.GPA = GPA;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
