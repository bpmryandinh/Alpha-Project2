package com.example.loginpage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApplication extends Application {
    // User is stored so that anything can access it if needed when they are logged in.
    public static UserLogin users;

    /*
    Creation of all the different fxml pages. Default page is set to home page
    and the scenes are all parsed into each other for the purpose of scene swapping
    in the future.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Creation of each fxml page
        FXMLLoader homePageLoader = new FXMLLoader(LoginApplication.class.getResource("HomePage.fxml"));
        Scene homeScene = new Scene(homePageLoader.load(), 380, 540);
        HomeController HomePageController = homePageLoader.getController();
        HomePageController.setStage(stage);


        FXMLLoader loginPageLoader = new FXMLLoader(LoginApplication.class.getResource("LoginPage.fxml"));
        Scene loginScene = new Scene(loginPageLoader.load(), 380, 540);
        LoginController loginPageController = loginPageLoader.getController();
        loginPageController.setStage(stage);

        FXMLLoader signUpPageLoader = new FXMLLoader(LoginApplication.class.getResource("SignUpPage.fxml"));
        Scene signupScene = new Scene(signUpPageLoader.load(), 380, 540);
        SignUpController SignUpPageController = signUpPageLoader.getController();
        SignUpPageController.setStage(stage);
        stage.setTitle("Awesome Page");

        // Parses in the scenes into eachothere for scene swapping
        HomePageController.setSignupScene(signupScene);
        HomePageController.setLoginScene(loginScene);
        loginPageController.setBackScene(homeScene);
        loginPageController.setHomeController(HomePageController);
        SignUpPageController.setBackScene(homeScene);
        stage.setScene(homeScene);
        stage.show();

        stage.getIcons().add(new Image("file:src/main/resources/images/smiley.png"));
    }


    public static void main(String[] args) {
        launch();
    }
}