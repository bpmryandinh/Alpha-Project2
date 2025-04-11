package com.example.loginpage;


import com.example.loginpage.Controllers.CoursePageController;
import com.example.loginpage.Controllers.StageController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Components {

    public static HBox createCard(String title, String author, String description, int views, int stars, String courseID, Scene courseScene) {

        HBox card = new HBox(10);
        card.setStyle("-fx-background-color: linear-gradient(to bottom left, #3d4548, #252829); -fx-padding: 15; -fx-border-color: #2c2525; -fx-border-width: 1; -fx-background-radius: 5;");
        card.setPrefHeight(80);

        VBox content = new VBox(5);

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Montserrat", 18));
        titleLabel.setStyle("-fx-font-weight: bold");
        Label authorLabel = new Label(author);
        authorLabel.setStyle("-fx-text-fill: #fffb00;");
        Label descLabel = new Label(description);
        descLabel.setFont(Font.font("Montserrat", 12));
        descLabel.setStyle("-fx-text-fill: #969191;");
//        descLabel.setStyle("-fx-font-weight: bold");

        content.getChildren().addAll(titleLabel, authorLabel, descLabel);

        HBox stats = new HBox(10);
        stats.setStyle("-fx-alignment: center;");

        stats.getChildren().addAll(
                new Label("\uD81A\uDE06 " + views),
                new Label("⭐ " + stars)
        );

        Button viewBtn = new Button("View");
        Button editBtn = new Button("Edit");

        viewBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CoursePageController.getInstance().courseID = courseID;
                StageController.getInstance().mainScene.setScene(courseScene);
            }
        });

        VBox buttons = new VBox(5, viewBtn, editBtn);
        buttons.setStyle("-fx-alignment: center;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        card.getChildren().addAll(new ImageView("https://via.placeholder.com/48"), content, spacer, stats, buttons);
        return card;
    }
}
