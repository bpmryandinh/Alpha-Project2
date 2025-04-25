package com.example.loginpage.Controllers;

import com.example.loginpage.Main;
import com.example.loginpage.Models.Course;
import com.example.loginpage.Models.User;
import com.example.loginpage.Services.FileService;
import com.example.loginpage.Services.HashService;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ListOptionsPageController {



    enum ListOptions {
        Course,
        Student
    }

    private Stage parentStage;
    private Scene rootScene;
    private Course course;
    private ListOptions listOptions;
    private ObservableList<Object> leftTableData = FXCollections.observableArrayList();
    private ObservableList<Object> rightTableData = FXCollections.observableArrayList();
    private CourseListPageController courseListPageController;

    @FXML
    private Label navbarUserText;
    @FXML
    private Label leftTableLabel;
    @FXML
    private Label rightTableLabel;

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

    public void onLogoutButtonPressed(ActionEvent actionEvent) {
        this.parentStage.setScene(Main.getHomeScene());
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setCourseListPageController(CourseListPageController courseListPageController) {
        this.courseListPageController = courseListPageController;
    }

    public void setData(Scene rootScene, String pageType) {
        navbarUserText.setText(Main.LoggedInUser.getuserType() + " View | " + "Welcome " + Main.LoggedInUser.getUser().getFname() + " " + Main.LoggedInUser.getUser().getLname() + " |");
        this.rootScene = rootScene;
        leftTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        rightTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        if (pageType.equals("student")) {
            listOptions = ListOptions.Student;
        } else if (pageType.equals("courses")) {
            listOptions = ListOptions.Course;
        }
        loadTableData();
        createCourseOptions();
    }

    public void loadTableData() {
        String[] ids;
        Object[] options;
        Object[] availableOptions;

        if (listOptions == ListOptions.Course) {
            ids = Main.LoggedInUser.getUser().getCourses();
            options = HashService.findCourses(ids);
        } else {
            ids = this.course.getUserIDs();
            options = HashService.findStudents(ids);
        }

        leftTableData.setAll(options);

        if (listOptions == ListOptions.Course) {
            availableOptions = HashService.getCourses();
        } else {
            availableOptions = HashService.getUsers();
        }

        List<Object> enrolledArr = new ArrayList<>(Arrays.asList(options));
        List<Object> availableArr = new ArrayList<>(Arrays.asList(availableOptions));

        availableArr.removeAll(enrolledArr);
        rightTableData.setAll(availableArr);
    }

    public void createCourseOptions() {
        String name;
        String classify;

        if (listOptions == ListOptions.Course) {
            name = "courseName";
            classify = "courseClassify";
        } else {
            name = "email";
            classify = "userID";
        }

        TableColumn leftselectColumn = new TableColumn<>("select");
        setProperties(leftselectColumn, 45, "Remove", true);
        TableColumn leftNameColumn = new TableColumn<>(name);
        setProperties(leftNameColumn, 180, listOptions.name() + " Name ", false);
        TableColumn leftClassifyColumn = new TableColumn<>(classify);
        setProperties(leftClassifyColumn, 80, listOptions.name() + " ID", false);

        TableColumn rightselectColumn = new TableColumn<>("select");
        setProperties(rightselectColumn, 45, "Add", true);
        TableColumn rightNameColumn = new TableColumn<>(name);
        setProperties(rightNameColumn, 180, listOptions.name() + " Name ", false);
        TableColumn rightClassifyColumn = new TableColumn<>(classify);
        setProperties(rightClassifyColumn, 80, listOptions.name() + " ID", false);


        leftTableLabel.setText("Current " + listOptions.name() + "s");
        rightTableLabel.setText("Available " + listOptions.name() + "s");
        leftTableView.getColumns().setAll(leftselectColumn, leftNameColumn, leftClassifyColumn);
        rightTableView.getColumns().setAll(rightselectColumn, rightNameColumn, rightClassifyColumn);

        leftselectColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("select"));
        leftNameColumn.setCellValueFactory(new PropertyValueFactory<Object, String>(name));
        leftClassifyColumn.setCellValueFactory(new PropertyValueFactory<Object, String>(classify));
        rightselectColumn.setCellValueFactory(new PropertyValueFactory<Object, String>("select"));
        rightNameColumn.setCellValueFactory(new PropertyValueFactory<Object, String>(name));
        rightClassifyColumn.setCellValueFactory(new PropertyValueFactory<Object, String>(classify));
        leftTableView.setItems(leftTableData);
        rightTableView.setItems(rightTableData);
    }

    public void onConfirmButtonPressed(ActionEvent actionEvent) throws IOException {
        ArrayList<Object> moveRight = new ArrayList<>();
        ArrayList<Object> moveLeft = new ArrayList<>();

        if (listOptions == ListOptions.Course) {
            for (Object item : leftTableView.getItems()) {
                Course course = (Course) item;
                if (course.getSelect().isSelected()) {
                    moveRight.add(course);
                    course.getSelect().setSelected(false);
                }
            }
            for (Object item : rightTableView.getItems()) {
                Course course = (Course) item;
                if (course.getSelect().isSelected()) {
                    moveLeft.add(course);
                    course.getSelect().setSelected(false);

                }
            }
            rightTableData.addAll(moveRight);
            leftTableData.removeAll(moveRight);
            rightTableData.removeAll(moveLeft);
            leftTableData.addAll(moveLeft);


            User user = Main.LoggedInUser.getUser();
            String[] newSave = new String[leftTableData.size()];

            for (int i = 0; i < newSave.length; i++) {
                Course course = (Course) leftTableData.get(i);
                newSave[i] = course.getCourseID();
                course.setUserIDs(new String[]{course.getUserIDs() + user.getUserID()});
            }

            user.setCourses(newSave);
            FileService.updateRecordCSV(user.getUserID(), user.getAllData());
            this.courseListPageController.reloadData();


        } else {
            for (Object item : leftTableView.getItems()) {
                User user = (User) item;
                if (user.getSelect().isSelected()) {
                    moveRight.add(user);
                    user.getSelect().setSelected(false);
                }
            }
            for (Object item : rightTableView.getItems()) {
                User user = (User) item;
                if (user.getSelect().isSelected()) {
                    moveLeft.add(user);
                    user.getSelect().setSelected(false);
                }
            }
            rightTableData.addAll(moveRight);
            leftTableData.removeAll(moveRight);
            rightTableData.removeAll(moveLeft);
            leftTableData.addAll(moveLeft);
            
//            String[] newSave = new String[leftTableData.size()];
//
//            for (int i = 0; i < newSave.length; i++) {
//                User user = (User) leftTableData.get(i);
//                newSave[i] = user.getUserID();
//                user.setCourses(new String[]{user.getCourses() + course.getCourseID()});
//
//            }
//
//            course.setUserIDs(newSave);
//            FileService.updateRecordCSV(course.getCourseID(), course.getAllData());
//            this.courseListPageController.reloadData();

        }
        }

    public void setProperties(TableColumn column, int width, String text, boolean centered) {
        column.setMinWidth(width);
        column.setText(text);
        if (centered) {
            column.setStyle("-fx-alignment: CENTER;");
        }
    }
}
