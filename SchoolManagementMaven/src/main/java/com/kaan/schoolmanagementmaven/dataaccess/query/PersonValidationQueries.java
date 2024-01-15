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
 * 
 */
public class PersonValidationQueries extends Query implements IPersonValidationQueries {

    private static IPersonValidationQueries personValidation;
    private IPersonLoginQueries personLoginQuery;

    static {
        personValidation = null;
    }

    private PersonValidationQueries() throws SQLException {
        personLoginQuery = PersonLoginQueries.getInstance();
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
        String query = getPersonNameAndLastnameQueryString(tableName);
        return super.runGettingQuery(query);
    }

    private String getPersonNameAndLastnameQueryString(String tableName) {
        return "select name , last_name from " + tableName + ";";
    }

    private Map<String, String> getPersonUsernameAndPhoneNumber(String personTable, String columnName) throws SQLException {
        String query = getPersonsUIDAndPhoneNumberQueryString(personTable);
        ResultSet UIDsAndPhoneNumbers = super.runGettingQuery(query);
        Map<String, String> userNameAndPhoneNumber = new HashMap();
        addUsernameAndPhoneNumberToMap(userNameAndPhoneNumber, UIDsAndPhoneNumbers, personTable, columnName);
        return userNameAndPhoneNumber;
    }

    private String getPersonsUIDAndPhoneNumberQueryString(String personTable) {
        return "select UID , phone_number from " + personTable + " ;";
    }

    private void addUsernameAndPhoneNumberToMap(Map<String, String> userNameAndPhoneNumber, ResultSet UIDsAndPhoneNumbers, String personTable, String columnName) throws SQLException {
        while (UIDsAndPhoneNumbers.next()) {
            int personUID = UIDsAndPhoneNumbers.getInt("UID");
            String userName = personLoginQuery.getPersonUsernameByUID(personUID, personTable, columnName);
            String phoneNumber = UIDsAndPhoneNumbers.getString("phone_number");
            userNameAndPhoneNumber.put(phoneNumber, userName);
        }
    }

    @Override
    public Map<String, String> getAllNormalStudentUsernameAndPhoneNumber() throws SQLException {
        return getPersonUsernameAndPhoneNumber(super.getAccess().getNormalStudentTable(), "normal_student_UID");
    }

    @Override
    public Map<String, String> getAllWorkingStudentUsernameAndPhoneNumber() throws SQLException {
        return getPersonUsernameAndPhoneNumber(super.getAccess().getWorkingStudentTable(), "working_student_UID");
    }

    @Override
    public Map<String, String> getAllTeacherUsernameAndPhoneNumber() throws SQLException {
        return getPersonUsernameAndPhoneNumber(super.getAccess().getTeacherTable(), "teacher_UID");
    }

}
