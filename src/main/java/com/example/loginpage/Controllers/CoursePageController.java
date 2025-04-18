package com.example.loginpage.Controllers;

import com.example.loginpage.Models.Course;
import com.example.loginpage.Models.User;
import com.example.loginpage.Services.HashService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CoursePageController {
    private static CoursePageController instance;
    private Stage parentStage;
    private Scene backScene;
    private String courseID;
    private Course course;
    private ObservableList<User> studentTableData = FXCollections.observableArrayList();


    @FXML
    private TableView<User> studentTable;
    @FXML
    private TableColumn<User, String> userID;
    @FXML
    private TableColumn<User, String> fname;
    @FXML
    private TableColumn<User, String> lname;
    @FXML
    private TableColumn<User, String> gender;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> gpa;
    @FXML
    private TableColumn<User, String> address;
    @FXML
    private TableColumn<User, String> phone;

    public CoursePageController() {
        this.parentStage = StageController.getInstance().mainScene;
        courseID = "";
    }

    public static CoursePageController getInstance() {
        if (instance == null) {
            instance = new CoursePageController();
        }
        return instance;
    }

    public void loadPageData() {
//            String[] studentIDs = {CoursePageController.getInstance().course.getUserIDs()};
//            User[] Users = HashService.findStudents(studentIDs);
//            CoursePageController.getInstance().studentTableData.clear();
//            CoursePageController.getInstance().studentTableData.addAll(Users);
    }

    public void setCourseClass() {
        String[] sendCourse = {CoursePageController.getInstance().getCourseID()};
        Course[] courseReturn = HashService.findCourses(sendCourse);
//        CoursePageController.getInstance().setCourse(courseReturn[0]);
        String[] studentIDs = {courseReturn[0].getUserIDs()};
        List<User> Users = List.of(HashService.findStudents(studentIDs));
        CoursePageController.getInstance().studentTableData.clear();
        CoursePageController.getInstance().studentTableData = FXCollections.observableArrayList(Users);
//        CoursePageController.getInstance().studentTableData.addAll(Users);
    }

    public void initialize() {
        userID.setCellValueFactory(new PropertyValueFactory<User, String>("userID"));
        fname.setCellValueFactory(new PropertyValueFactory<User, String>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<User, String>("lname"));
        gender.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        gpa.setCellValueFactory(new PropertyValueFactory<User, String>("gpa"));
        address.setCellValueFactory(new PropertyValueFactory<User, String>("address"));
        phone.setCellValueFactory(new PropertyValueFactory<User, String>("phone"));
        studentTable.setItems(studentTableData);
    }

    public void refreshPage() {
        loadPageData();
    }





    public void setBackScene(Scene backScene) {
        this.backScene = backScene;
    }


    public void onBackButtonPressed(ActionEvent actionEvent) {
        this.parentStage.setScene(this.backScene);
    }

    public String getCourseID() {
        return CoursePageController.getInstance().courseID;
    }

    public void setCourseID(String courseID) {
        CoursePageController.getInstance().courseID = courseID;
    }

    public Course getCourse() {
        return CoursePageController.getInstance().course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    //    public void loadCourseData(){}
}

