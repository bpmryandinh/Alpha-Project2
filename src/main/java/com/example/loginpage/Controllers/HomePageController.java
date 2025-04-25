package com.example.loginpage.Controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/* Main landing page controller that manages user navigation and welcome display.
   Handles transitions between login/signup pages and course management features. */
public class HomePageController {
    @FXML
    private Button signUpButton;
    @FXML
    private Button logInButton;
    @FXML
    private Button courseListButton;
    @FXML
    private Label welcomeLabel;

    private Scene signupScene;
    private Scene loginScene;
    private Scene courseList;
    private CourseListPageController courseListController;

    /* Configures the signup scene for navigation.
       Enables transition to user registration flow. */
    public void setSignupScene(Scene scene) {
        signupScene = scene;
    }

    /* Configures the login scene for navigation.
       Enables transition to user authentication flow. */
    public void setLoginScene(Scene scene) {
        loginScene = scene;
    }

    /* Sets up the course list view for navigation.
       Enables access to course management features. */
    public void setCourseList(Scene scene) {
        courseList = scene;
    }

    /* Links the course list controller for data management.
       Enables communication between home and course list views. */
    public void setCourseListController(CourseListPageController controller) {
        courseListController = controller;
    }

    /* Handles signup button click event.
       Transitions user to the registration page. */
    public void signUpClicked() {
        Stage stage = (Stage) signUpButton.getScene().getWindow();
        stage.setScene(signupScene);
    }

    /* Handles login button click event.
       Transitions user to the authentication page. */
    public void logInClicked() {
        Stage stage = (Stage) logInButton.getScene().getWindow();
        stage.setScene(loginScene);
    }

    /* Updates the welcome message with user information.
       Customizes the display based on login status. */
    public void refresh(String username) {
        welcomeLabel.setText("Welcome " + username + "!");
    }

    /* Handles course list button click event.
       Transitions to course management interface. */
    public void CourseListButtonPressed() {
        Stage stage = (Stage) courseListButton.getScene().getWindow();
        stage.setScene(courseList);
        courseListController.reloadData();
    }
}
