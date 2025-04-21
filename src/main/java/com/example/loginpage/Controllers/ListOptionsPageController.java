package com.example.loginpage.Controllers;

import com.example.loginpage.Main;
import com.example.loginpage.Models.Course;
import com.example.loginpage.Models.User;
import com.example.loginpage.Services.HashService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListOptionsPageController {
//    enum ListOptions {
//        ADD_OPTION,
//        REMOVE_OPTION
//    }

    private Stage parentStage;
    private Scene rootScene;
//    private ListOptions listOptions;
    private ObservableList<Object> leftTableData = FXCollections.observableArrayList();
    private ObservableList<Object> rightTableData = FXCollections.observableArrayList();

    @FXML
    private TableView<Object> leftTableView;
    @FXML
    private TableView<Object> rightTableView;

    public ListOptionsPageController() {
        this.parentStage = StageController.getInstance().mainScene;
    }

    public void onBackButtonPressed(ActionEvent actionEvent) {
        StageController.getInstance().mainScene.setScene(rootScene);
    }

    public void setData(Scene rootScene, String pageType) {
        this.rootScene = rootScene;
        leftTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        rightTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        if (pageType.equals("student")) {
        } else if (pageType.equals("courses")) {
        loadTableData();
        createCourseOptions();
        }
    }

    public void loadTableData() {
        String[] userCoursesIDs = Main.LoggedInUser.getUser().getCourses();
        Course[] enrolledCourses = HashService.findCourses(userCoursesIDs);
        leftTableData.setAll(enrolledCourses);

        Course[] availableCourses = HashService.getCourses();

        List<Course> enrolledCoursesArr = new ArrayList<>(Arrays.asList(enrolledCourses));
        List<Course> availableCoursesArr = new ArrayList<>(Arrays.asList(availableCourses));

        availableCoursesArr.removeAll(enrolledCoursesArr);
        rightTableData.setAll(availableCoursesArr);
    }

    public void createCourseOptions() {
        TableColumn leftselectColumn = new TableColumn<>("select");
        leftselectColumn.setMinWidth(40);
        leftselectColumn.setText("Remove");
        leftselectColumn.setStyle("-fx-alignment: CENTER;");
        TableColumn leftcourseNameColumn = new TableColumn<>("courseName");
        leftcourseNameColumn.setMinWidth(180);
        leftcourseNameColumn.setText("Course Name ");
        TableColumn leftcourseClassifyColumn = new TableColumn<>("courseClassify");
        leftcourseClassifyColumn.setMinWidth(80);
        leftcourseClassifyColumn.setText("Course ID");

        TableColumn rightselectColumn = new TableColumn<>("select");
        rightselectColumn.setMinWidth(60);
        rightselectColumn.setText("Add");
        rightselectColumn.setStyle("-fx-alignment: CENTER;");
        TableColumn rightcourseNameColumn = new TableColumn<>("courseName");
        rightcourseNameColumn.setMinWidth(180);
        rightcourseNameColumn.setText("Course Name ");
        TableColumn rightcourseClassifyColumn = new TableColumn<>("courseClassify");
        rightcourseClassifyColumn.setMinWidth(85);
        rightcourseClassifyColumn.setText("Course ID");


        leftTableView.getColumns().setAll(leftselectColumn, leftcourseNameColumn, leftcourseClassifyColumn);
        rightTableView.getColumns().setAll(rightselectColumn, rightcourseNameColumn, rightcourseClassifyColumn);

        leftselectColumn.setCellValueFactory(new PropertyValueFactory<User, String>("select"));
        leftcourseNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("courseName"));
        leftcourseClassifyColumn.setCellValueFactory(new PropertyValueFactory<User, String>("courseClassify"));
        rightselectColumn.setCellValueFactory(new PropertyValueFactory<User, String>("select"));
        rightcourseNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("courseName"));
        rightcourseClassifyColumn.setCellValueFactory(new PropertyValueFactory<User, String>("courseClassify"));
        leftTableView.setItems(leftTableData);
        rightTableView.setItems(rightTableData);
    }
}
