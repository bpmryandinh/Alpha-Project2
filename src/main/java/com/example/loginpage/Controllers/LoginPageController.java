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

public class LoginPageController {
    // Stages and Scenes for the purpose of scene swapping
    private Stage parentStage;
    private Scene backScene;
    private HomePageController homeController;

    @FXML
    private TextField emailTxtField;

    @FXML
    private PasswordField passwordTxtField;

    @FXML
    private Label errorField;

    /*
    Reads the loginData and splices the data into a separated string array based on the deliminated commas.
    It then loops through and checks the second index of each chunk through decryption to see if the user
    typed email is available. Based on availability errors or shown or the passwords are doublechecked
    through the secure methods. If information is correct user is logged on.
     */
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

        while (emailFound != true || i > lines.length) {
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
            // profID needs to be a separated from LoggedInUser
            // change LoggedInUser to studentLoginUser and professorLoginUser
            if (hashedPassword.equals(lines[i + 4])) {
                String strSalt = lines[i];
                String id = SecureMiddleware.decrypt(lines[i+1], SecureMiddleware.secret);
                String profID = SecureMiddleware.decrypt(lines[i+2], SecureMiddleware.secret);
                String email = SecureMiddleware.decrypt(lines[i+3], SecureMiddleware.secret);
                String name = SecureMiddleware.decrypt(lines[i+4], SecureMiddleware.secret);
                Main.LoggedInUser = new UserSession(strSalt, id, email, name, hashedPassword);
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

    public void setBackScene(Scene scene) {
        this.backScene = scene;
    }

    public void setHomeController(HomePageController homeController) {
        this.homeController = homeController;
    }

    private void switchScene() {
        parentStage.setScene(backScene);
    }

    public void backButtonClick(ActionEvent actionEvent) {
        switchScene();

    }
}