package com.example.loginpage.Controllers;

import com.example.loginpage.Main;
import com.example.loginpage.Models.Course;
import com.example.loginpage.Models.Professor;
import com.example.loginpage.Models.Student;
import com.example.loginpage.Services.HashService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CoursePageController {
    private Stage parentStage;
    private Scene backScene;
    private String courseID;
    private Course course;
//    private ObservableList<User> studentTableData = FXCollections.observableArrayList();

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

