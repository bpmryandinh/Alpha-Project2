package com.example.loginpage.Models;

import com.example.loginpage.Services.HashService;// A virtual user just for holding the users data temporarily and for future use.
public class UserSession {
    public enum Person {
        Student,
        Professor
    }

    private String salt;
    private String ID;
    private String email;
    private String name;
    private String hashedPassword;
    private Student student;
    private Professor professor;
    public Person userType;

    public UserSession(String salt, String ID, String email, String name, String hashedPassword ) {
        setSalt(salt);
        setID(ID);
        setEmail(email);
        setName(name);
        setHashedPassword(hashedPassword);
        setPersonData();
    }

    public void setPersonData () {
        if (ID.charAt(0) == 'P')
        {
            this.userType = Person.Professor;
            setProfessor();
        } else {
            this.userType = Person.Student;
            setStudent();
        }
    }

    public Student getStudent() {
        return student;
    }

    public User getUser() {
        if (userType == Person.Student) {
            return this.student;
        } else {
            return this.professor;
        }
    }

    public void setStudent() {
        Student[] student = HashService.findStudents(new String[]{ID});
        this.student = student[0];
    }

    public void setProfessor() {
        this.professor = HashService.findProfessor(ID);
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
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

}
