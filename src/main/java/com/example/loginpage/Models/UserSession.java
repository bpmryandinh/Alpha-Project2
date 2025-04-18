package com.example.loginpage.Models;

import com.example.loginpage.Services.HashService;// A virtual user just for holding the users data temporarily and for future use.
public class UserSession {
    enum Person {
        Student,
        Professor
    }

    private String salt;
    private String userID;
    private String email;
    private String name;
    private String hashedPassword;
    private User user;
    public Person userType;

    public UserSession(String salt, String userID, String email, String name, String hashedPassword ) {
        this.salt = salt;
        this.userID = userID;
        this.email = email;
        this.name = name;
        this.hashedPassword = hashedPassword;
        setUser();
    }

    public void setUser() {
            User[] user = HashService.findStudents(new String[]{userID});
            this.user = user[0];
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getHashedPassword() {
        return hashedPassword;
    }
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }
    public User getUser() {
        return this.user;
    }
}
