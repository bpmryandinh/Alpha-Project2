package com.example.loginpage;

import com.example.loginpage.Controllers.*;
import com.example.loginpage.Models.Student;
import com.example.loginpage.Models.UserSession;
import com.example.loginpage.Services.FileService;
import com.example.loginpage.Services.HashService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    // User is stored so that anything can access it if needed when they are logged in.
    public static UserSession LoggedInUser;

    public static String testUserID = "B1015";
    public static Student testStudent;

    public static Scene homeScene;
    public static LoginPageController loginPageController;

    /*
    Creation of all the different fxml pages. Default page is set to home page
    and the scenes are all parsed into each other for the purpose of scene swapping
    in the future.
     */


    @Override
    public void start(Stage stage) throws IOException {

//        LoggedInUser = new UserSession("salt", "B1001", "<EMAIL>", "test", "hash");

        // Creation of each fxml page
        StageController.setInstance(new StageController(), stage);

        FXMLLoader homePageLoader = new FXMLLoader(Main.class.getResource("HomePage.fxml"));
        homeScene = new Scene(homePageLoader.load(), 380, 540);
        HomePageController homePageController = homePageLoader.getController();

        FXMLLoader loginPageLoader = new FXMLLoader(Main.class.getResource("LoginPage.fxml"));
        Scene loginScene = new Scene(loginPageLoader.load(), 380, 540);
        loginPageController = loginPageLoader.getController();

        FXMLLoader signUpPageLoader = new FXMLLoader(Main.class.getResource("SignUpPage.fxml"));
        Scene signupScene = new Scene(signUpPageLoader.load(), 380, 540);
        SignupPageController SignUpPageController = signUpPageLoader.getController();

        FXMLLoader courseListPageLoader = new FXMLLoader(Main.class.getResource("CourseListPage.fxml"));
        Scene courseListScene = new Scene(courseListPageLoader.load(), 800, 600);
        CourseListPageController courseListPageController = courseListPageLoader.getController();

        FXMLLoader coursePageLoader = new FXMLLoader(Main.class.getResource("CoursePage.fxml"));
        Scene coursePageScene = new Scene(coursePageLoader.load(), 800, 600);
        CoursePageController coursePageController = coursePageLoader.getController();

        FXMLLoader listOptionsPageLoader = new FXMLLoader(Main.class.getResource("ListOptionsPage.fxml"));
        Scene listOptionsPageScene = new Scene(listOptionsPageLoader.load(), 800, 400);
        ListOptionsPageController listOptionsPageController = listOptionsPageLoader.getController();
        stage.setTitle("Awesome Page");

        // Parses in the scenes into each other for scene swapping
        homePageController.setSignupScene(signupScene);
        homePageController.setLoginScene(loginScene);
        homePageController.setCourseList(courseListScene);
        homePageController.setCourseListController(courseListPageController);
        loginPageController.setBackScene(homeScene);
        loginPageController.setCourseList(courseListScene);
        loginPageController.setHomeController(homePageController);
        loginPageController.setCourseListController(courseListPageController);
        SignUpPageController.setBackScene(homeScene);
        courseListPageController.setCourseScene(coursePageScene);
        courseListPageController.setSelfScene(courseListScene);
        courseListPageController.setCoursePageController(coursePageController);
        courseListPageController.setListOptionsPageScene(listOptionsPageScene);
        courseListPageController.setListOptionsPageController(listOptionsPageController);
        coursePageController.setBackScene(courseListScene);
        coursePageController.setSelfScene(coursePageScene);
        coursePageController.setListOptionsPageScene(listOptionsPageScene);
        coursePageController.setListOptionsPageController(listOptionsPageController);
        listOptionsPageController.setCourseListPageController(courseListPageController);
        listOptionsPageController.setCoursePageController(coursePageController);
        stage.setScene(homeScene);
        stage.show();

        stage.getIcons().add(new Image("file:src/main/resources/images/smiley.png"));


        HashService.writeProfessorHashMap(FileService.readAllCSV("professors"));
        HashService.writeUserHashMap(FileService.readAllCSV("users"));
        HashService.writeCourseDataHashMap(FileService.readAllCSV("coursesData"));
        HashService.writeCourseHashMap(FileService.readAllCSV("courses"));

        // For testing the CourseListLoader
//        User[] user = HashService.findStudents(new String[]{testUserID});
//        testUser = user[0];
//        LoggedInUser = new UserSession("salt", "P1005", "<EMAIL>", "test", "hash");
//
//        LoggedInUser.setUserID("B1030");
//        LoggedInUser.setUser();

    }

    public static Scene getHomeScene() {
        return homeScene;
    }

    public static LoginPageController getLoginPageController() {
        return loginPageController;
    }


    public static void main(String[] args) {
        launch();
    }
}