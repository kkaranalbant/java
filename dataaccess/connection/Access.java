/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author kaan
 */
public class Access implements IAccess {

    private String host;
    private String userName;
    private String pass;
    private String dbName;
    private int port;

    private String adminTable;
    private String normalStudentTable;
    private String workingStudentTable;
    private String teacherTable;
    private String defaultValuesTable;
    private String normalStudentLoginInfosTable;
    private String normalStudentCourseTable;
    private String workingStudentCourseTable;
    private String workingStudentLoginInfosTable;
    private String teacherBranchTable;
    private String teacherLoginInfos;
    private String lessonTable;
    private String attendanceTable ;
    private String normalStudentExamTable ;
    private String workingStudentExamTable ;

    private static Access access = null;
    private Connection connection;

    private Access() throws SQLException  { 
        host = DefaultDatabaseInfos.host;
        userName = DefaultDatabaseInfos.userName;
        pass = DefaultDatabaseInfos.pass;
        dbName = DefaultDatabaseInfos.dbName;
        port = DefaultDatabaseInfos.port;

        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        connection = DriverManager.getConnection(url, userName, pass);

        adminTable = DefaultDatabaseInfos.adminTable;
        normalStudentTable = DefaultDatabaseInfos.normalStudentTable;
        teacherTable = DefaultDatabaseInfos.teacherTable;
        workingStudentTable = DefaultDatabaseInfos.workingStudentTable;
        defaultValuesTable = DefaultDatabaseInfos.defaultValuesTable;
        normalStudentCourseTable = DefaultDatabaseInfos.normalStudentCourseTable;
        normalStudentLoginInfosTable = DefaultDatabaseInfos.normalStudentLoginInfosTable;
        workingStudentCourseTable = DefaultDatabaseInfos.workingStudentCourseTable;
        workingStudentLoginInfosTable = DefaultDatabaseInfos.workingStudentLoginInfosTable;
        teacherBranchTable = DefaultDatabaseInfos.teacherBranchTable;
        teacherLoginInfos = DefaultDatabaseInfos.teacherLoginInfos;
        lessonTable = DefaultDatabaseInfos.lessonTable;
        attendanceTable =DefaultDatabaseInfos.attendanceTable ;
        normalStudentExamTable = DefaultDatabaseInfos.normalStudentExamTable ;
        workingStudentExamTable = DefaultDatabaseInfos.workingStudentExamTable ;
    }

    public static Access getInstance() throws SQLException  {
        if (access == null) {
            access = new Access();
        }
        return access;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    public String getAdminTable() {
        return adminTable;
    }

    public String getNormalStudentTable() {
        return normalStudentTable;
    }

    public String getWorkingStudentTable() {
        return workingStudentTable;
    }

    public String getTeacherTable() {
        return teacherTable;
    }

    public String getDefaultValuesTable() {
        return defaultValuesTable;
    }

    public String getNormalStudentLoginInfosTable() {
        return normalStudentLoginInfosTable;
    }

    public String getNormalStudentCourseTable() {
        return normalStudentCourseTable;
    }

    public String getWorkingStudentCourseTable() {
        return workingStudentCourseTable;
    }

    public String getWorkingStudentLoginInfosTable() {
        return workingStudentLoginInfosTable;
    }

    public String getTeacherBranchTable() {
        return teacherBranchTable;
    }

    public String getTeacherLoginInfos() {
        return teacherLoginInfos;
    }

    public String getLessonTable() {
        return lessonTable;
    }

    public String getAttendanceTable() {
        return attendanceTable;
    }

    public String getNormalStudentExamTable() {
        return normalStudentExamTable;
    }

    public String getWorkingStudentExamTable() {
        return workingStudentExamTable;
    }

    void setHost(String host) {
        this.host = host;
    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    void setPass(String pass) {
        this.pass = pass;
    }

    void setDbName(String dbName) {
        this.dbName = dbName;
    }

    void setPort(int port) {
        this.port = port;
    }

    void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    

}
