package com.example.loginpage.Controllers;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CoursePageController {
    private static CoursePageController instance;
    private final Stage parentStage;
    public Scene backScene;
    public static String courseID;



    public CoursePageController() {
        this.parentStage = StageController.getInstance().mainScene;
        this.courseID = "";
    }

    public static CoursePageController getInstance() {
        if (instance == null) {
            instance = new CoursePageController();
        }
        return instance;
    }

    public void setBackScene(Scene backScene) {
        this.backScene = backScene;
    }


    public void onBackButtonPressed(ActionEvent actionEvent) {
            this.parentStage.setScene(this.backScene);
        }
    }
