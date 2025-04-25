package com.example.loginpage.Controllers;
import com.example.loginpage.Components;

import com.example.loginpage.Main;
import com.example.loginpage.Models.Course;
import com.example.loginpage.Services.HashService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/* Controller that manages the display and interaction with the list of courses.
   Handles course card creation, navigation between views, and data updates. */
public class CourseListPageController {
    private Stage parentStage;
    private Scene selfScene;
    private Scene courseScene;
    private CoursePageController coursePageController;
    private Scene listOptionsPageScene;
    private ListOptionsPageController listOptionsPageController;

    @FXML
    private Label navbarUserText;
    @FXML
    private ScrollPane courseScrollPane;
    @FXML
    private VBox courseVBox;

    public CourseListPageController() {
        this.parentStage = StageController.getInstance().mainScene;
    }

    /* Creates a new course card in the list view.
       Currently disabled in implementation. */
    public void createCardButtonPressed(ActionEvent actionEvent) {
//        courseVBox.getChildren().add(Components.createCard("ALGORITHM DESIGN AND PROGRAMMING II", "CSCI 1614", "2025 01 Spring Basic Computer Science Course", 20, 1,"C5012", courseScene));
    }

    /* Refreshes the display with current course data.
       Updates navbar with user info and rebuilds course cards. */
    public void reloadData() {
        navbarUserText.setText(Main.LoggedInUser.getuserType() + " View | " + "Welcome " + Main.LoggedInUser.getUser().getFname() + " " + Main.LoggedInUser.getUser().getLname() + " |");
        String[] userCoursesIDs = Main.LoggedInUser.getUser().getCourses();
        Course[] userCourses = HashService.findCourses(userCoursesIDs);
        courseVBox.getChildren().clear();
        for(Course userCourse : userCourses){
            String[] userCourseData = userCourse.getCourseData();
            courseVBox.getChildren().add(Components.createCard(userCourseData[1], userCourseData[2], userCourseData[3], userCourseData[4], userCourseData[5],userCourseData[0], courseScene, coursePageController));
        }
    }

    /* Sets up the scene for viewing individual course details.
       Links to the detailed course view. */
    public void setCourseScene(Scene scene) {
        this.courseScene = scene;
    }

    /* Links the controller for individual course pages.
       Enables communication between list and detail views. */
    public void setCoursePageController(CoursePageController coursePageController) {
        this.coursePageController = coursePageController;
    }

    /* Sets up the scene for course list modification options.
       Used when editing course enrollments. */
    public void setListOptionsPageScene(Scene listOptionsPageScene) {
        this.listOptionsPageScene = listOptionsPageScene;
    }

    /* Handles navigation to course list editing interface.
       Transitions to the list options view for course management. */
    public void editCourseListButtonPressed(ActionEvent actionEvent) {
        StageController.getInstance().mainScene.setScene(listOptionsPageScene);
        listOptionsPageController.setData(this.selfScene, "courses");
    }

    /* Links the controller for list modification options.
       Enables communication with the list editing interface. */
    public void setListOptionsPageController(ListOptionsPageController listOptionsPageController) {
        this.listOptionsPageController = listOptionsPageController;
    }

    /* Sets the reference to this controller's main view.
       Used for navigation and state management. */
    public void setSelfScene(Scene courseListScene) {
        this.selfScene = courseListScene;
    }

    /* Handles user logout action.
       Returns to the application home screen. */
    public void onLogoutButtonPressed(ActionEvent actionEvent) {
        this.parentStage.setScene(Main.getHomeScene());
    }
}
