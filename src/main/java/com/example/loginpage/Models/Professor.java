package com.example.loginpage.Models;

public class Professor extends IUser {

    public Professor(String userID, String rawCourses, String fname, String lname, String gender, String email, String address, String birthday, String phone) {
        super(userID, rawCourses,fname, lname, gender, email, address, birthday, phone);
    }
}
