package com.example.loginpage.Services;

import com.example.loginpage.Models.Course;
import com.example.loginpage.Models.Professor;
import com.example.loginpage.Models.Student;

import java.util.HashMap;

public class HashService {
    /*
    * 2 separate methods to fill in data for course and user hashmaps
    * Integer = ID of course or user (depending on method)
    * */

    // Key:Value Pairs
    //
    private static final HashMap<String, Course> Courses = new HashMap<>();
    private static final HashMap<String, Student> Users = new HashMap<>();
    private static final HashMap<String, Professor> Professors = new HashMap<>();
    private static final HashMap<String, String[]> CourseData = new HashMap<>();


    // Student Session: Sending the user course Data
    public static Course[] findCourses(String[] CourseID) {
        Course[] CourseReturn =  new Course[CourseID.length];
        for(int i = 0; i < CourseID.length; i++){
            CourseReturn[i] = Courses.get(CourseID[i]);
        }
        return CourseReturn;
    }

    public static String[] findCourseData(String CourseID) {
        return CourseData.get(CourseID);
    }

    // Professor Session: Sending UserData for a course
    public static Student[] findStudents(String[] UserID) {
        Student[] studentReturn = new Student[UserID.length];
        for(int i = 0; i < UserID.length; i++){
            studentReturn[i] = Users.get(UserID[i]);
        }
        return studentReturn;
    }

    public static Professor findProfessor(String professorID) {
        return Professors.get(professorID);
    }

    // Method to fill in data for course hashmap
    public static void writeCourseHashMap(String[][] CourseSData) {
        for(String[] courseData : CourseSData){
            Course course = new Course(courseData[0], courseData[1], courseData[2],findCourseData(courseData[0]), findProfessor(courseData[3]));
            Courses.put(courseData[0], course);
        }
    }

    public static void writeCourseDataHashMap(String[][] CourseSPageData) {
        for(String[] course: CourseSPageData){
            CourseData.put(course[0], course);
        }
    }

    // Method to fill in data for user hashmap
    public static void writeUserHashMap(String[][] UserSData){
        for(String[] userData : UserSData){
            Student student = new Student(userData[0],userData[1],userData[2],userData[3],userData[4],userData[5],userData[6],userData[7],userData[8],userData[9],userData[10]);
            Users.put(userData[0], student);
        }
    }

    public static void writeProfessorHashMap(String[][] ProfessorSData){
        for(String[] professorData : ProfessorSData){
            Professor professor = new Professor(professorData[0],professorData[1],professorData[2],professorData[3],professorData[4],professorData[5],professorData[6],professorData[7],professorData[8]);
            Professors.put(professorData[0], professor);
        }
    }

    public static Course[] getCourses() {
        return Courses.values().toArray(new Course[Courses.size()]);
    }

    public static Student[] getUsers() {
        return Users.values().toArray(new Student[Users.size()]);
    }

    public static Boolean checkStudentExistence(String CheckID){
        return Users.containsKey(CheckID);
    }
}
