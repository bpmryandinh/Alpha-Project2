package com.example.loginpage.Controllers;

import com.example.loginpage.Main;
import com.example.loginpage.Models.*;
import com.example.loginpage.Services.FileService;
import com.example.loginpage.Services.HashService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class CoursePageController {
    private Stage parentStage;
    private Scene backScene;
    private String courseID;
    private Course course;

//    private ObservableList<User> studentTableData = FXCollections.observableArrayList();

    //region Label FXML
    @FXML
    private Label navbarUserText;
    @FXML
    private Label courseNameLabel;
    @FXML
    private Label courseProfessorLabel;
    @FXML
    private Label courseIDLabel;
    @FXML
    private Label courseInfoLabel;
    @FXML
    private Label professorInfoLabel;
    @FXML
    private Label studentIdLabel;
    //endregion

    //region Table FXML
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> userID;
    @FXML
    private TableColumn<Student, String> fname;
    @FXML
    private TableColumn<Student, String> lname;
    @FXML
    private TableColumn<Student, String> gender;
    @FXML
    private TableColumn<Student, String> email;
    @FXML
    private TableColumn<Student, String> gpa;
    @FXML
    private TableColumn<Student, String> courses;
    @FXML
    private TableColumn<Student, String> year;
    //endregion

    //region TextField FXML
    @FXML
    private TextField fnameTextField;
    @FXML
    private TextField lnameTextField;
    @FXML
    private TextField genderTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField gpaTextField;
    //endregion



    public CoursePageController() {
        this.parentStage = StageController.getInstance().mainScene;
        courseID = "";
    }


    public ObservableList<Student> loadPageData() {
            String[] studentIDs = course.getUserIDs();
            Student[] students = HashService.findStudents(studentIDs);
            return FXCollections.observableArrayList(students);
    }

    public void setCourseClass() {
        String[] sendCourse = {getCourseID()};
        Course[] courseReturn = HashService.findCourses(sendCourse);
        setCourse(courseReturn[0]);
    }


    public void refreshPage() {
        userID.setCellValueFactory(new PropertyValueFactory<Student, String>("userID"));
        fname.setCellValueFactory(new PropertyValueFactory<Student, String>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<Student, String>("lname"));
        gender.setCellValueFactory(new PropertyValueFactory<Student, String>("gender"));
        email.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        gpa.setCellValueFactory(new PropertyValueFactory<Student, String>("GPA"));
        courses.setCellValueFactory(new PropertyValueFactory<Student, String>("CoursesString"));
        year.setCellValueFactory(new PropertyValueFactory<Student, String>("year"));
        studentTable.setItems(loadPageData());
        setPageValues();
    }

    public void setPageValues() {
        Professor professor = course.getCourseProfessor();
        navbarUserText.setText("Professor View | " + "Welcome " + Main.LoggedInUser.getUser().getFname() + " " + Main.LoggedInUser.getUser().getLname() + " |");
        courseNameLabel.setText(course.getCourseName());
        courseProfessorLabel.setText("Professor " + professor.getFname() + " " + professor.getLname());
        courseIDLabel.setText(course.getCourseData()[2]);
        courseInfoLabel.setText(course.getCourseData()[3]);
        professorInfoLabel.setText("Hi, Welcome to my course " + course.getCourseName().toLowerCase() + "! My name is Professor " + professor.getFname() + " " + professor.getLname() + " | This is my contact info if you need to get in touch | " + professor.getEmail() + " | " + professor.getPhone());

        if (Main.LoggedInUser.userType == UserSession.Person.Student) {
            fillStudentFields(Main.LoggedInUser.getUser());
        } else {
            studentTable.setRowFactory(tv -> {
                TableRow<Student> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY
                            && event.getClickCount() == 2) {

                        Student selectedStudent = row.getItem();
                        fillStudentFields(selectedStudent);
                    }
                });
                return row ;
            });
        }
    }


    public void fillStudentFields(User student) {
        studentIdLabel.setText(student.getUserID());
        fnameTextField.setText(student.getFname());
        lnameTextField.setText(student.getLname());
        genderTextField.setText(student.getGender());
        emailTextField.setText(student.getEmail());
    }







    public void onSaveButtonClicked(ActionEvent actionEvent) throws IOException {
        //Get values
        String studentId = studentIdLabel.getText();
        String fname = fnameTextField.getText();
        String lname = lnameTextField.getText();
        String gender = genderTextField.getText();
        String email = emailTextField.getText();

        String[] data = null;
        if (Main.LoggedInUser.userType == UserSession.Person.Student) {
            Main.LoggedInUser.getStudent().setUserID(studentId);
            Main.LoggedInUser.getStudent().setFname(fname);
            Main.LoggedInUser.getStudent().setLname(lname);
            Main.LoggedInUser.getStudent().setGender(gender);
            Main.LoggedInUser.getStudent().setEmail(email);
            data = Main.LoggedInUser.getStudent().getStudentData();
        } else {
            Student selectedstudent = HashService.findStudent(studentId);
            selectedstudent.setUserID(studentId);
            selectedstudent.setFname(fname);
            selectedstudent.setLname(lname);
            selectedstudent.setGender(gender);
            selectedstudent.setEmail(email);
            data = selectedstudent.getStudentData();
        }

        FileService.updateRecordCSV(studentId, data);
    }

    public void setBackScene(Scene backScene) {
        this.backScene = backScene;
    }

    public void onBackButtonPressed(ActionEvent actionEvent) {
        this.parentStage.setScene(this.backScene);
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    //    public void loadCourseData(){}
}

