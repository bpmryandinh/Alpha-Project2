package com.example.loginpage;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Components {

    public static HBox createCard(String title, String author, String description, int views, int stars, int forks) {
        HBox card = new HBox(10);
        card.setStyle("-fx-background-color: white; -fx-padding: 15; -fx-border-color: #ccc; -fx-border-width: 1; -fx-background-radius: 5;");
        card.setPrefHeight(80);

        VBox content = new VBox(5);

        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", 18));
        Label authorLabel = new Label(author);
        authorLabel.setStyle("-fx-text-fill: blue;");
        Label descLabel = new Label(description);
        descLabel.setFont(Font.font("Arial", 12));
        descLabel.setStyle("-fx-text-fill: gray;");

        content.getChildren().addAll(titleLabel, authorLabel, descLabel);

        HBox stats = new HBox(10);
        stats.setStyle("-fx-alignment: center;");

        stats.getChildren().addAll(
                new Label("\uD81A\uDE06 " + views),
                new Label("⭐ " + stars),
                new Label("🍴 " + forks)
        );

        Button viewBtn = new Button("View");
        Button editBtn = new Button("Edit");

        VBox buttons = new VBox(5, viewBtn, editBtn);
        buttons.setStyle("-fx-alignment: center;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        card.getChildren().addAll(new ImageView("https://via.placeholder.com/48"), content, spacer, stats, buttons);
        return card;
    }
}
