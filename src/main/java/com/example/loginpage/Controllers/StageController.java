package com.example.loginpage.Controllers;

import javafx.stage.Stage;

/* Singleton controller that manages the main application window.
   Provides centralized access to the primary stage for scene switching. */
public class StageController {
    private static StageController instance;
    public static Stage mainScene;

    /* Provides access to the singleton controller instance.
       Ensures only one controller exists for the application. */
    public static StageController getInstance() {
        return instance;
    }

    /* Initializes the singleton controller with main stage reference.
       Sets up the primary window for scene management. */
    public static void setInstance(StageController instance, Stage mainScene) {
        StageController.instance = instance;
        StageController.mainScene = mainScene;
    }
//
//    this.parentStage.setScene(this.signupScene);
}
