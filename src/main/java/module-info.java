module com.example.loginpage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.loginpage to javafx.fxml;
    exports com.example.loginpage;
    exports com.example.loginpage.Controllers;
    opens com.example.loginpage.Controllers to javafx.fxml;
    exports com.example.loginpage.Services;
    opens com.example.loginpage.Services to javafx.fxml;
    exports com.example.loginpage.Models;
    opens com.example.loginpage.Models to javafx.fxml;

}