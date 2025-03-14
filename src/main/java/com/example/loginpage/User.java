package com.example.loginpage;

// A virtual user just for holding the users data temporarily and for future use.
public class User {
    private String salt;
    private String email;
    private String name;
    private String hashedPassword;

    public User(String salt, String email, String name, String hashedPassword ) {
        this.salt = salt;
        this.email = email;
        this.name = name;
        this.hashedPassword = hashedPassword;

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
