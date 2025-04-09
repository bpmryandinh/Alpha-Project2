package com.example.loginpage.Controllers;
import com.example.loginpage.Components;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CourseListPageController {

    private Stage parentStage;

    @FXML
    private VBox courseVBox;


    public void createCardButtonPressed(ActionEvent actionEvent) {
        courseVBox.getChildren().add(Components.createCard("ALGORITHM DESIGN AND PROGRAMMING II", "CSCI 1614", "2025 01 Spring Basic Computer Science Course", 20, 1, 1));
    }

    public void setStage(Stage stage) {
        this.parentStage = stage;
    }
}
