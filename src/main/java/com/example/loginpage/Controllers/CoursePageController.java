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
import java.util.Objects;

public class CoursePageController {
    private Stage parentStage;
    private Scene backScene;
    private Scene listOptionsPageScene;
    private ListOptionsPageController listOptionsPageController;
    private Scene selfScene;
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

    @FXML
    private ComboBox<String> yearComboBox;

    private final String[] yearChoices = {"Freshman", "Sophomore", "Junior", "Senior"};



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
        navbarUserText.setText(Main.LoggedInUser.getuserType() + " View | " + "Welcome " + Main.LoggedInUser.getUser().getFname() + " " + Main.LoggedInUser.getUser().getLname() + " |");
        courseNameLabel.setText(course.getCourseName());
        courseProfessorLabel.setText("Professor " + professor.getFname() + " " + professor.getLname());
        courseIDLabel.setText(course.getCourseData()[2]);
        courseInfoLabel.setText(course.getCourseData()[3]);
        professorInfoLabel.setText("Hi, Welcome to my course " + course.getCourseName().toLowerCase() + "! My name is Professor " + professor.getFname() + " " + professor.getLname() + " | This is my contact info if you need to get in touch | " + professor.getEmail() + " | " + professor.getPhone());

        //Set the list of years as the options for the ComboBox
        yearComboBox.setValue(" ");
        ObservableList<String> items =
                FXCollections.observableArrayList(yearChoices);
        yearComboBox.setItems(items);

        /*
        If the Logged-In User is a student, fillStudentFields() is called with the logged-in user
        This means that the TextFields to update records is locked to being only the info of the logged-in student
        Meaning that if you're logged in as a student you can ONLY edit your own record.
         */
        if (Objects.equals(Main.LoggedInUser.getuserType(), UserSession.Person.Student.name())) {
            fillStudentFields(Main.LoggedInUser.getStudent());
        } else { //If the Logged-In user is a professor, the fillStudentFields() is called with the Student selected from the studentTable
            studentTable.setRowFactory(tv -> { //Creates a studentTable row factory using a lambda function
                TableRow<Student> row = new TableRow<>();   //creates a new TableRow of Student Data Type
                row.setOnMouseClicked(event -> {    //Sets the row when mouse is clicked (new lambda Function)
                    if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY
                            && event.getClickCount() == 2) {    //Makes it have to be a double click

                        Student selectedStudent = row.getItem();    //Stores the selected row as a Student Type
                        fillStudentFields(selectedStudent);     //Calls fillStudentFields() with the selectedStudent variable
                    }
                });
                return row ;    //  returns row for the second lambda function
            });
        }
    }


    //Fills student label/fields with the data from the passed in User
    public void fillStudentFields(Student student) {
        studentIdLabel.setText(student.getUserID());
        fnameTextField.setText(student.getFname());
        lnameTextField.setText(student.getLname());
        genderTextField.setText(student.getGender());
        emailTextField.setText(student.getEmail());
        gpaTextField.setText(student.getGPA());
        yearComboBox.setValue(student.getYear());
    }

    //Returns a String[] with updated student data
    public String[] updateUserData(Student student) {
        String studentId = studentIdLabel.getText();
        String fname = fnameTextField.getText();
        String lname = lnameTextField.getText();
        String gender = genderTextField.getText();
        String gpa = gpaTextField.getText();
        String email = emailTextField.getText();
        String year = yearComboBox.getValue();

        String[] data = null;

        //Sets Student data to whatever is written in the text fields
        student.setUserID(studentId);
        student.setFname(fname);
        student.setLname(lname);
        student.setGender(gender);
        student.setGPA(gpa);
        student.setEmail(email);
        student.setYear(year);

        //Gets (now updated) user data from the Student Class
        data = student.getAllData();
        return data;
    }

    public void onSaveButtonClicked(ActionEvent actionEvent) throws IOException {
        String studentId = studentIdLabel.getText();
        String[] data;

        //If logged-in user is a student, updateUserData() is called using LoggedInUser
        if (Main.LoggedInUser.getuserType() == UserSession.Person.Student.name()) {
            data = updateUserData(Main.LoggedInUser.getStudent());
        } else {    //If logged-in user is a professor, updateUserData() is called using the student from the studentID Label
            Student selectedstudent = HashService.findStudent(studentId);
            data = updateUserData(selectedstudent);
        }

        //calls updateRecordCSV() using data and Student ID from label
        FileService.updateRecordCSV(studentId, data);
        refreshPage();
    }

    public void setBackScene(Scene backScene) {
        this.backScene = backScene;
    }

    public void setListOptionsPageScene(Scene listOptionsPageScene) {
        this.listOptionsPageScene = listOptionsPageScene;
    }

    public void setListOptionsPageController(ListOptionsPageController listOptionsPageController) {
        this.listOptionsPageController = listOptionsPageController;
    }

    public void setSelfScene(Scene selfScene) {
        this.selfScene = selfScene;
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

    public void addStudentButtonPressed(ActionEvent actionEvent) {
        StageController.getInstance().mainScene.setScene(listOptionsPageScene);
        listOptionsPageController.setCourse(this.course);
        listOptionsPageController.setData(this.selfScene, "student");
    }

    public void onLogoutButtonPressed(ActionEvent actionEvent) {
        this.parentStage.setScene(Main.getHomeScene());
    }


    //    public void loadCourseData(){}
}

