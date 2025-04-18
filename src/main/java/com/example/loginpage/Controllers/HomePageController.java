package com.example.loginpage.Controllers;

import com.example.loginpage.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/*
Home Controller manages navigation between the login page and signup page.
It also welcomes the user once they are finally logged in.
 */
public class HomePageController {
    // Stages and Scenes for the purpose of scene swapping
    private final Stage parentStage;
    private Scene signupScene;
    private Scene loginScene;
    private Scene courseListScene;

    @FXML
    private Label welcomeUserId;

    public HomePageController() {
        this.parentStage = StageController.getInstance().mainScene;
    }

//    public void setStage(Stage stage) {
//
//    }

    public void setSignupScene(Scene scene) {
        this.signupScene = scene;
    }

    public void setLoginScene(Scene scene) {
        this.loginScene = scene;
    }

    public void setCourseList(Scene scene) {
        this.courseListScene = scene;
    }

    public void signUpClicked(ActionEvent actionEvent) {
        this.parentStage.setScene(this.signupScene);
    }

    public void logInClicked(ActionEvent actionEvent) {
        this.parentStage.setScene(this.loginScene);
    }

    // Is called whenever the user is finally logged in
    public void refresh(){
        if (Main.user != null) {
            welcomeUserId.setText("Welcome, " + Main.user.getName());
        }
    }


    public void CourseListButtonPressed(ActionEvent actionEvent) {
        StageController.getInstance().mainScene.setScene(this.courseListScene);
//        CourseListPageController.reloadData();
    }
}
