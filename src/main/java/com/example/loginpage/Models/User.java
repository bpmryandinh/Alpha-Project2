package com.example.loginpage.Models;

public class User {
    private String userID;
    private String[] Courses;
    private String fname;
    private String lname;
    private String gender;
    private String email;
    private String address;
    private String birthday;
    private String phone;
    private String coursesRaw;

    public User(String userID, String coursesRaw,String fname, String lname, String gender, String email, String address, String birthday, String phone) {
        this.userID = userID;
        this.Courses = splitCourses(coursesRaw);
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.phone = phone;

        this.coursesRaw = coursesRaw;
    }


    public void setUserID(String userID) {
        this.userID = userID;
    }
    public void setCourses(String[] courses) {
        Courses = courses;
    }
    public void setFname(String fname) {
        this.fname = fname;
    }
    public void setLname(String lname) {
        this.lname = lname;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String[] splitCourses(String coursesRaw) {
        return coursesRaw.split(";");
    }
    public String[] getCourses() {
        return Courses;
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
    public String getAddress() {
        return address;
    }
    public String getBirthday() {
        return birthday;
    }
    public String getPhone() {
        return phone;
    }
    public String[] getData() {
        return new String[]{userID, coursesRaw, fname, lname, gender, email, address, birthday, phone}; }
}
