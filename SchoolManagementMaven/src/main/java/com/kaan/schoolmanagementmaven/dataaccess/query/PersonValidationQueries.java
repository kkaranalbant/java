/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kaan
 */
public class PersonValidationQueries extends Query implements IPersonValidationQueries {

    private static IPersonValidationQueries personValidation;

    static {
        personValidation = null;
    }

    private PersonValidationQueries() throws SQLException {

    }

    public static IPersonValidationQueries getInstance() throws SQLException {
        if (personValidation == null) {
            personValidation = new PersonValidationQueries();
        }
        return personValidation;
    }

    @Override
    public ResultSet getAllNormalStudentNameAndLastname() throws SQLException {
        return getPersonNameAndLastname(super.getAccess().getNormalStudentTable());
    }

    @Override
    public ResultSet getAllWorkingStudentNameAndLastname() throws SQLException {
        return getPersonNameAndLastname(super.getAccess().getWorkingStudentTable());
    }

    @Override
    public ResultSet getAllTeacherNameAndLastname() throws SQLException {
        return getPersonNameAndLastname(super.getAccess().getTeacherTable());
    }

    private ResultSet getPersonNameAndLastname(String tableName) throws SQLException {
        String query = "select name , last_name from " + tableName + ";";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeQuery();
    }

    private Map<String, String> getPersonUserameAndPhoneNumber(String personTable, String loginInfoTable, String columnName) throws SQLException {
        String query = "select UID , phone_number from " + personTable + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        Map<String, String> result = new HashMap();
        while (resultSet.next()) {
            query = "select username from " + loginInfoTable + " where " + columnName + " = " + resultSet.getInt("UID");
            super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
            ResultSet resultSetForUsername = super.getPreparedStatement().executeQuery();
            resultSetForUsername.next();
            result.put(resultSet.getString("phone_number"), resultSetForUsername.getString("username"));
        }
        return result;
    }

    @Override
    public Map<String, String> getAllNormalStudentUsernameAndPhoneNumber() throws SQLException {
        return getPersonUserameAndPhoneNumber(super.getAccess().getNormalStudentTable(), super.getAccess().getNormalStudentLoginInfosTable(), "normal_student_UID");
    }

    @Override
    public Map<String, String> getAllWorkingStudentUsernameAndPhoneNumber() throws SQLException {
        return getPersonUserameAndPhoneNumber(super.getAccess().getWorkingStudentTable(), super.getAccess().getWorkingStudentLoginInfosTable(), "working_student_UID");
    }

    @Override
    public Map<String, String> getAllTeacherUsernameAndPhoneNumber() throws SQLException {
        return getPersonUserameAndPhoneNumber(super.getAccess().getTeacherTable(), super.getAccess().getTeacherLoginInfos(), "teacher_UID");
    }

}
