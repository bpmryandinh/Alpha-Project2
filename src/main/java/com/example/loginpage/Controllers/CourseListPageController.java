package com.example.loginpage.Controllers;
import com.example.loginpage.Components;

import com.example.loginpage.Main;
import com.example.loginpage.Models.Course;
import com.example.loginpage.Services.HashService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CourseListPageController {
//    private static CourseListPageController instance;

    private Stage parentStage;
    private Scene courseScene;
    private CoursePageController coursePageController;


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
        String[] userCoursesIDs = Main.testUser.getCourses();
        Course[] userCourses = HashService.findCourses(userCoursesIDs);
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
}
