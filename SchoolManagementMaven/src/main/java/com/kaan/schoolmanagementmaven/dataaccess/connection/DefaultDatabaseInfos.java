/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.connection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author kaan
 * 
 */
public class DefaultDatabaseInfos {

    private static File databaseFile;
    private static FileReader fileReader;
    private static BufferedReader databaseInfoReader;

    public static String host ;
    public static String userName ;
    public static String pass ;
    public static String dbName ;
    public static int port ;
    
    
    static String adminTable = "admin";
    static String normalStudentTable = "normal_students";
    static String workingStudentTable = "working_students";
    static String teacherTable = "teachers";
    static String defaultValuesTable = "default_values";
    static String normalStudentLoginInfosTable = "normal_students_login_infos";
    static String normalStudentCourseTable = "normal_students_course";
    static String workingStudentCourseTable = "working_students_course";
    static String workingStudentLoginInfosTable = "working_students_login_infos";
    static String teacherBranchTable = "teacher_branch";
    static String teacherLoginInfos = "teacher_login_infos";
    static String lessonTable = "lesson";
    static String attendanceTable = "course_attendance";
    static String normalStudentExamTable = "normal_student_exam_notes";
    static String workingStudentExamTable = "working_student_exam_notes";
}
