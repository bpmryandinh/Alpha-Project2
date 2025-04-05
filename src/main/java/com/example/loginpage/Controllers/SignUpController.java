package com.example.loginpage.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
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

public class SignUpController {
    // Stages and Scenes for the purpose of scene swapping
    private Stage parentStage;
    private Scene backScene;

    @FXML
    private TextField emailTxtField;

    @FXML
    private TextField nameTxtField;

    @FXML
    private PasswordField passwordTxtField;

    /*
    Submit button reads through the loginData file to take the past user data
    and then appends the new encrypted data based on user input for the final
    signup process.
     */
    @FXML
    public void submitButtonClick(ActionEvent actionEvent) throws IOException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {

        String fileName = "src/main/resources/data/userLoginData.csv";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = reader.readLine();
        reader.close();
        if (line == null) {
            line = "";
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        // Encrypts everything with the secret key or with salt if it is the password
        byte[] salt = SecureController.getSalt();
        String encryptedEmail = SecureController.encrypt(emailTxtField.getText(), SecureController.secret);
        String encryptedName = SecureController.encrypt(nameTxtField.getText(), SecureController.secret);
        String hashedPassword = SecureController.getSecurePassword(passwordTxtField.getText(), salt);

        // Everything appends to the past user data and the salt is encoded into a string
        writer.write(line + Base64.getEncoder().encodeToString(salt) + "," + encryptedEmail + "," + encryptedName + "," + hashedPassword + ",");
        writer.close();
        switchScene();
    }

    // These classes are for scene managing and swapping between the controller scenes.
    public void setStage(Stage stage) {
        this.parentStage = stage;
    }

    public void setBackScene(Scene scene) {
        this.backScene = scene;
    }

    private void switchScene() {
        parentStage.setScene(backScene);
    }

    public void backButtonClick(ActionEvent actionEvent) {
        switchScene();
    }
}
