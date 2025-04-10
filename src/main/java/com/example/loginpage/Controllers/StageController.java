package com.example.loginpage.Controllers;

import javafx.stage.Stage;

public class StageController {
    private static StageController instance;
    public static Stage mainScene;

    public static StageController getInstance() {
        return instance;
    }

    public static void setInstance(StageController instance, Stage mainScene) {
        StageController.instance = instance;
        StageController.mainScene = mainScene;
    }
//
//    this.parentStage.setScene(this.signupScene);
}
