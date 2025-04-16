package com.example.loginpage.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CoursePageController {
    private static CoursePageController instance;
    private Stage parentStage;
    public Scene backScene;
    public static String courseID;


    @FXML
    private TableColumn<String, String> studentColumn;
    @FXML
    private TableColumn<String, String> fnameColumn;
    @FXML
    private TableColumn<String, String> lnameColumn;
    @FXML
    private TableColumn<String, String> genderColumn;
    @FXML
    private TableColumn<String, String> emailColumn;
    @FXML
    private TableColumn<String, String> gpaColumn;
    @FXML
    private TableColumn<String, String> addressColumn;
    @FXML
    private TableColumn<String, String> phoneColumn;


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

    public void loadCourseData(){}
    }

