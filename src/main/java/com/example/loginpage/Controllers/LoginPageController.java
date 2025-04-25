package com.example.loginpage.Controllers;

import com.example.loginpage.Main;
import com.example.loginpage.Models.UserSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/* Controller that manages user authentication and login process.
   Handles credential verification, secure login, and session creation. */
public class LoginPageController {
    // Stages and Scenes for the purpose of scene swapping
    private Stage parentStage;
    private Scene backScene;
    private Scene courseListScene;
    private HomePageController homeController;
    private CourseListPageController courseListPageController;

    @FXML
    private TextField idTxtField;

    @FXML
    private TextField emailTxtField;

    @FXML
    private PasswordField passwordTxtField;

    @FXML
    private Label errorField;


    /* Processes login form submission and authenticates user credentials.
       Verifies email/password combination and creates user session on success. */
    @FXML
    public void submitButtonClick(ActionEvent actionEvent) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String inputtedEmail = emailTxtField.getText();
        String inputtedPassword = passwordTxtField.getText();
        String fileEmail;
        byte[] salt;
        String decryptedEmail;

        String fileName = "src/main/resources/data/userLoginData.csv";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        String[] lines = line.split(",");

        // Keeps track if a matching email is found
        boolean emailFound = false;
        int i = 0;

        while (emailFound != true && i < lines.length - 1) {
             fileEmail = lines[i + 1];
             decryptedEmail = SecureMiddleware.decrypt(fileEmail, SecureMiddleware.secret);

             if (decryptedEmail.equals(inputtedEmail)) {
                 emailFound = true;
             } else {
                 // Adds 4 because user data is seperated in chunks of 4 techically
                 i += 4;
             }
        }

        if (!emailFound) {
            errorField.setText("Error Incorrect Email or Password");
        } else {
            // Salt needs to get decoded with this as the default byte conversion was inconsistent
            salt = Base64.getDecoder().decode(lines[i]);
            String hashedPassword = SecureMiddleware.getSecurePassword(inputtedPassword, salt);
            // A new user object is created and the user is considered logged in if hashes are equal
            if (hashedPassword.equals(lines[i + 3])) {
                String strSalt = lines[i];
                String email = SecureMiddleware.decrypt(lines[i+1], SecureMiddleware.secret);
                String name = SecureMiddleware.decrypt(lines[i+2], SecureMiddleware.secret);
                Main.LoggedInUser = new UserSession(strSalt, idTxtField.getText(), email, name, hashedPassword);
                switchScene();
                homeController.refresh();

            } else {
                errorField.setText("Incorrect Password");
            }
        }
        reader.close();

        // These classes are for scene managing and swapping between the controller scenes.
    }
    public LoginPageController() {
        this.parentStage = StageController.getInstance().mainScene;
    }

    /* Links the course list controller for post-login navigation.
       Enables access to course management interface. */
    public void setCourseListController(CourseListPageController courseListPageController) {
        this.courseListPageController = courseListPageController;
    }

    /* Sets up the course list view for successful login.
       Prepares the main application interface. */
    public void setCourseList(Scene scene) {
        this.courseListScene = scene;
    }

    /* Sets the scene to return to when canceling login.
       Part of the navigation flow management. */
    public void setBackScene(Scene scene) {
        this.backScene = scene;
    }

    /* Links the home controller for navigation purposes.
       Enables communication with the home page interface. */
    public void setHomeController(HomePageController homeController) {
        this.homeController = homeController;
    }

    /* Handles transition to course list after successful login.
       Updates the view and refreshes course data. */
    private void switchScene() {
        StageController.getInstance().mainScene.setScene(this.courseListScene);
        this.courseListPageController.reloadData();
    }

    /* Handles navigation back to previous screen.
       Cancels the login process. */
    public void backButtonClick(ActionEvent actionEvent) {
        parentStage.setScene(backScene);
    }

    /* Resets all input fields to empty state.
       Used when clearing the login form. */
    public void clearFXML() {
        idTxtField.setText("");
        emailTxtField.setText("");
        passwordTxtField.setText("");
    }
}