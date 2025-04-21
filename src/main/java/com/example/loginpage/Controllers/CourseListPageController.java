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

public class CourseListPageController {
//    private static CourseListPageController instance;

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


//    public static CourseListPageController getInstance() {
//        if (instance == null) {
//            instance = new CourseListPageController();
//        }
//        return instance;
//    }

    public void createCardButtonPressed(ActionEvent actionEvent) {
//        courseVBox.getChildren().add(Components.createCard("ALGORITHM DESIGN AND PROGRAMMING II", "CSCI 1614", "2025 01 Spring Basic Computer Science Course", 20, 1,"C5012", courseScene));
    }

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


    public void setCourseScene(Scene scene) {
        this.courseScene = scene;
    }

    public void setCoursePageController(CoursePageController coursePageController) {
        this.coursePageController = coursePageController;
    }

    public void setListOptionsPageScene(Scene listOptionsPageScene) {
        this.listOptionsPageScene = listOptionsPageScene;
    }

    public void editCourseListButtonPressed(ActionEvent actionEvent) {
        StageController.getInstance().mainScene.setScene(listOptionsPageScene);
        listOptionsPageController.setData(this.selfScene, "courses");
    }

    public void setListOptionsPageController(ListOptionsPageController listOptionsPageController) {
        this.listOptionsPageController = listOptionsPageController;
    }

    public void setSelfScene(Scene courseListScene) {
        this.selfScene = courseListScene;
    }

    public void onLogoutButtonPressed(ActionEvent actionEvent) {
        this.parentStage.setScene(Main.getHomeScene());
    }
}
