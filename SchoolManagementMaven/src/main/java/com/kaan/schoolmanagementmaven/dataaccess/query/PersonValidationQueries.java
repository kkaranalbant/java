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
    private IPersonFetchingQueries personFetcher;

    static {
        personValidation = null;
    }

    private PersonValidationQueries() throws SQLException {
        personLoginQuery = PersonLoginQueries.getInstance();
        personFetcher = PersonFetchingQueries.getInstance();
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

    private Map<String, String> getPersonUsernameAndPhoneNumber(String personTable) throws SQLException {
        String query = getPersonsUIDAndPhoneNumberQueryString(personTable);
        ResultSet UIDsAndPhoneNumbers = super.runGettingQuery(query);
        Map<String, String> userNameAndPhoneNumber = new HashMap();
        addUsernameAndPhoneNumberToMap(userNameAndPhoneNumber, UIDsAndPhoneNumbers);
        return userNameAndPhoneNumber;
    }

    private String getPersonsUIDAndPhoneNumberQueryString(String personTable) {
        return "select UID , phone_number from " + personTable + " ;";
    }

    private void addUsernameAndPhoneNumberToMap(Map<String, String> userNameAndPhoneNumber, ResultSet UIDsAndPhoneNumbers) throws SQLException {
        while (UIDsAndPhoneNumbers.next()) {
            int personUID = UIDsAndPhoneNumbers.getInt("UID");
            String userName = personLoginQuery.getPersonUsernameByUID(personUID);
            String phoneNumber = UIDsAndPhoneNumbers.getString("phone_number");
            userNameAndPhoneNumber.put(phoneNumber, userName);
        }
    }

    @Override
    public Map<String, String> getAllNormalStudentUsernameAndPhoneNumber() throws SQLException {
        return getPersonUsernameAndPhoneNumber(super.getAccess().getNormalStudentTable());
    }

    @Override
    public Map<String, String> getAllWorkingStudentUsernameAndPhoneNumber() throws SQLException {
        return getPersonUsernameAndPhoneNumber(super.getAccess().getWorkingStudentTable());
    }

    @Override
    public Map<String, String> getAllTeacherUsernameAndPhoneNumber() throws SQLException {
        return getPersonUsernameAndPhoneNumber(super.getAccess().getTeacherTable());
    }

    @Override
    public boolean isValidUIDForNormalStudentTable(int uid) throws SQLException {
        ResultSet normalStudents = personFetcher.getAllNormalStudentInfo();
        return isValidUID(normalStudents, uid);
    }

    @Override
    public boolean isValidUIDForWorkingStudentTable(int uid) throws SQLException {
        ResultSet workingStudents = personFetcher.getAllWorkingStudentInfo();
        return isValidUID(workingStudents, uid);
    }

    @Override
    public boolean isValidUIDForTeacherTable(int uid) throws SQLException {
        ResultSet teachers = personFetcher.getAllTeacherInfo();
        return isValidUID(teachers, uid);
    }

    private boolean isValidUID(ResultSet persons, int uid) throws SQLException {
        while (persons.next()) {
            int personUID = persons.getInt("UID");
            if (personUID == uid) {
                return true;
            }
        }
        return false;
    }

}
