/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.dataaccess.query.IStudentInformationQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.ITeacherInformationQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonInformationQuery;
import java.sql.ResultSet;

/**
 *
 * @author kaan
 */
public class AdminPersonInformationManager implements IAdminStudentInformationManager, IAdminTeacherInformationManager {

    private static AdminPersonInformationManager infoManager;
    private ITeacherInformationQueries teacherInfo;
    private IStudentInformationQueries studentInfo;

    private AdminPersonInformationManager() throws SQLException{
        teacherInfo = PersonInformationQuery.getInstanceForTeacher();
        studentInfo = PersonInformationQuery.getInstanceForStudent();
    }

    public static IAdminStudentInformationManager getInstanceForStudent() throws SQLException {
        if (infoManager == null) {
            infoManager = new AdminPersonInformationManager();
        }
        return infoManager;
    }

    public static IAdminTeacherInformationManager getInstanceForTeacher() throws SQLException {
        if (infoManager == null) {
            infoManager = new AdminPersonInformationManager();
        }
        return infoManager;
    }

    @Override
    public String normalStudentInfo() throws SQLException {
        ResultSet resultSet = studentInfo.getAllNormalStudentNameLastnameAndUID();
        StringBuilder sb = new StringBuilder();
        while (resultSet.next()) {
            sb.append("name : ").append(resultSet.getString("name")).append("\n").append("Lastname : ").append(resultSet.getString("last_name")).append("\n").append("UID : ").append(resultSet.getInt("UID")).append("\n\n");
        }
        return sb.toString();
    }

    @Override
    public String workingStudentInfo() throws SQLException {
        ResultSet resultSet = studentInfo.getAllWorkingStudentNameLastnameAndUID();
        StringBuilder sb = new StringBuilder();
        while (resultSet.next()) {
            sb.append("name : ").append(resultSet.getString("name")).append("\n").append("Lastname : ").append(resultSet.getString("last_name")).append("\n").append("UID : ").append(resultSet.getInt("UID")).append("\n\n");
        }
        return sb.toString();
    }

    @Override
    public String teacherInfo() throws SQLException {
        ResultSet resultSet = teacherInfo.getAllTeacherNameLastnameAndUID() ;
        StringBuilder sb = new StringBuilder();
        while (resultSet.next()) {
            sb.append("name : ").append(resultSet.getString("name")).append("\n").append("Lastname : ").append(resultSet.getString("last_name")).append("\n").append("UID : ").append(resultSet.getInt("UID")).append("\n\n");
        }
        return sb.toString();
    }

}
