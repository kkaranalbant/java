/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;

/**
 *
 * @author kaan
 * 
 */
public class TableCreatingQuery extends Query implements ITableCreatingQuery {

    private static ITableCreatingQuery tableCreator;

    static {
        tableCreator = null;
    }

    private TableCreatingQuery() throws SQLException {

    }

    public static ITableCreatingQuery getInstance() throws SQLException {
        if (tableCreator == null) {
            tableCreator = new TableCreatingQuery();
        }
        return tableCreator;
    }

    
    
    @Override
    public void addAllTables () throws SQLException{
        addAdminTable();
        addNormalStudentsTable();
        addWorkingStudentsTable();
        addTeacherTable();
        addLessonTable();
        addNormalStudentCourse();
        addWorkingStudentCourse();
        addNormalStudentsLoginInfo();
        addWorkingStudentsLoginInfo();
        addTeacherLoginInfo();
        addNormalStudentExamNote();
        addWorkingStudentExamNote();
        addDefaultValues();
        addCourseAttendance();
        addTeacherBranch();
    }
    
     private void addAdminTable() throws SQLException {
        String query = "create table admin ("
                + "username varchar(25),"
                + "pass varchar(25)"
                + ");";
        super.getAccess().getConnection().prepareStatement(query).executeUpdate();
    }

    private void addNormalStudentsTable() throws SQLException {
        String query = "create table normal_students ("
                + "name varchar(25),"
                + "last_name varchar(25),"
                + "UID int primary key ,"
                + "balance int,"
                + "debt int,"
                + "student_lesson_credit int,"
                + "phone_number char (13)"
                + ");";
        super.getAccess().getConnection().prepareStatement(query).executeUpdate();
    }

    private void addWorkingStudentsTable() throws SQLException {
        String query = "create table working_students ("
                + "name varchar(25),"
                + "last_name varchar(25),"
                + "UID int primary key ,"
                + "balance int,"
                + "debt int,"
                + "student_lesson_credit int,"
                + "phone_number char (13)"
                + ");";
        super.getAccess().getConnection().prepareStatement(query).executeUpdate();
    }

    private void addTeacherTable() throws SQLException {
        String query = "create table teachers ("
                + "name varchar(25),"
                + "last_name varchar(25),"
                + "UID int primary key ,"
                + "balance int,"
                + "salary int,"
                + "phone_number char (13)"
                + ");";
        super.getAccess().getConnection().prepareStatement(query).executeUpdate();
    }

    private void addLessonTable() throws SQLException {
        String query = "create table lesson ("
                + "lesson_name varchar(20),"
                + "lesson_credit int,"
                + "lesson_hour int,"
                + "lesson_UID int primary key,"
                + "quota int,"
                + "average_midterm_rate int ,"
                + "average_final_rate int"
                + ");";
        super.getAccess().getConnection().prepareStatement(query).executeUpdate();
    }

    private void addNormalStudentCourse() throws SQLException {
        String query = "create table normal_students_course("
                + "normal_student_UID int,"
                + "lesson_UID int ,"
                + "teacher_UID int ,"
                + "foreign key (normal_student_UID) references normal_students(UID) ,"
                + "foreign key (lesson_UID) references lesson(lesson_UID),"
                + "foreign key (teacher_UID) references teachers (UID)"
                + ");";
        super.getAccess().getConnection().prepareStatement(query).executeUpdate();
    }

    private void addWorkingStudentCourse() throws SQLException {
        String query = "create table working_students_course("
                + "working_student_UID int,"
                + "lesson_UID int ,"
                + "teacher_UID int ,"
                + "foreign key (working_student_UID) references working_students(UID) ,"
                + "foreign key (lesson_UID) references lesson(lesson_UID),"
                + "foreign key (teacher_UID) references teachers (UID)"
                + ");";
        super.getAccess().getConnection().prepareStatement(query).executeUpdate();
    }

    private void addNormalStudentsLoginInfo() throws SQLException {
        String query = "create table normal_students_login_infos ("
                + "normal_student_UID int primary key,"
                + "username varchar (25) ,"
                + "pass varchar (25),"
                + "foreign key (normal_student_UID) references normal_students (UID)"
                + ");";
        super.getAccess().getConnection().prepareStatement(query).executeUpdate();
    }

    private void addWorkingStudentsLoginInfo() throws SQLException {
        String query = "create table working_students_login_infos ("
                + "working_student_UID int primary key,"
                + "username varchar (25) ,"
                + "pass varchar (25),"
                + "foreign key (working_student_UID) references working_students (UID)"
                + ");";
        super.getAccess().getConnection().prepareStatement(query).executeUpdate();
    }

    private void addTeacherLoginInfo() throws SQLException {
        String query = "create table teacher_login_infos ("
                + "teacher_UID int primary key ,"
                + "username varchar (25),"
                + "pass varchar(25),"
                + "foreign key (teacher_UID) references teachers (UID)"
                + ");";
        super.getAccess().getConnection().prepareStatement(query).executeUpdate();
    }

    private void addTeacherBranch() throws SQLException {
        String query = "create table teacher_branch ("
                + "teacher_UID int primary key,"
                + "lesson_UID int,"
                + "foreign key (teacher_UID) references teachers (UID) ,"
                + "foreign key (lesson_UID) references lesson(lesson_UID)"
                + ");";
        super.getAccess().getConnection().prepareStatement(query).executeUpdate();
    }

    private void addNormalStudentExamNote() throws SQLException {
        String query = "create table normal_student_exam_notes ("
                + "student_UID int ,"
                + "lesson_UID int ,"
                + "primary key (student_UID , lesson_UID) ,"
                + "foreign key (student_UID) references normal_students (UID) ,"
                + "foreign key (lesson_UID) references lesson (lesson_UID),"
                + "midterm int ,"
                + "final int ,"
                + "average int "
                + ");";
        super.getAccess().getConnection().prepareStatement(query).executeUpdate();
    }

    private void addWorkingStudentExamNote() throws SQLException {
        String query = "create table working_student_exam_notes ("
                + "student_UID int ,"
                + "lesson_UID int ,"
                + "primary key (student_UID , lesson_UID) ,"
                + "foreign key (student_UID) references working_students (UID) ,"
                + "foreign key (lesson_UID) references lesson (lesson_UID),"
                + "midterm int ,"
                + "final int ,"
                + "average int "
                + ");";
        super.getAccess().getConnection().prepareStatement(query).executeUpdate();
    }

    private void addCourseAttendance() throws SQLException {
        String query = "create table course_attendance ("
                + "lesson_UID int primary key,"
                + "attendance int,"
                + "foreign key (lesson_UID) references lesson(lesson_UID)"
                + ");";
        super.getAccess().getConnection().prepareStatement(query).executeUpdate();
    }

    private void addDefaultValues() throws SQLException {
        String query = "create table default_values ("
                + "default_lesson_credit int,"
                + "default_max_debt_credit int,"
                + "default_balance int ,"
                + "default_debt int ,"
                + "default_normal_student_UID_origin int ,"
                + "default_normal_student_UID_bound int ,"
                + "default_working_student_UID_origin int ,"
                + "default_working_student_UID_bound int ,"
                + "default_teacher_UID_origin int ,"
                + "default_teacher_UID_bound int ,"
                + "default_lesson_UID_origin int ,"
                + "default_lesson_UID_bound int ,"
                + "cost_for_per_hour int "
                + ");";
        super.getAccess().getConnection().prepareStatement(query).executeUpdate();
        IFirstTimeDefaultInfoQuery rowAdder = FirstTimeDefaultInfoQuery.getInstance();
        rowAdder.addDefaultValuesRow();
    }

}
