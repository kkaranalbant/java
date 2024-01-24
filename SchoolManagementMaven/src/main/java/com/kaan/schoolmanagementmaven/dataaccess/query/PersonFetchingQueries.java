/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kaan
 *
 */
public class PersonFetchingQueries extends Query implements IPersonFetchingQueries {

    private static IPersonFetchingQueries studentCreatingQuery = null;
    private static final int INVALID_UID;

    static {
        INVALID_UID = -1;
    }

    private PersonFetchingQueries() throws SQLException {
    }

    public static IPersonFetchingQueries getInstance() throws SQLException {
        if (studentCreatingQuery == null) {
            studentCreatingQuery = new PersonFetchingQueries();
        }
        return studentCreatingQuery;
    }

    @Override
    public ResultSet getNormalStudentInfo(int uid) throws SQLException {
        String query = getNormalStudentInfoQueryString(uid);
        return super.runGettingQuery(query);
    }

    private String getNormalStudentInfoQueryString(int uid) {
        return "select * from " + super.getAccess().getNormalStudentTable() + " where UID = " + uid;
    }

    @Override
    public ResultSet getWorkingStudentInfo(int uid) throws SQLException {
        String query = getWorkingStudentInfoQueryString(uid);
        return super.runGettingQuery(query);
    }

    private String getWorkingStudentInfoQueryString(int uid) {
        return "select * from " + super.getAccess().getWorkingStudentTable() + " where UID = " + uid;
    }

    @Override
    public ResultSet getTeacherInfo(int uid) throws SQLException {
        String query = getTeacherInfoQueryString(uid);
        return super.runGettingQuery(query);
    }

    private String getTeacherInfoQueryString(int uid) {
        return "select * from " + super.getAccess().getTeacherTable() + " where UID = " + uid;
    }

    @Override
    public ResultSet getAllNormalStudentInfo() throws SQLException {
        return getAllPersonInfo(super.getAccess().getNormalStudentTable());
    }

    @Override
    public ResultSet getAllWorkingStudentInfo() throws SQLException {
        return getAllPersonInfo(super.getAccess().getWorkingStudentTable());
    }

    @Override
    public ResultSet getAllTeacherInfo() throws SQLException {
        return getAllPersonInfo(super.getAccess().getTeacherTable());
    }

    private ResultSet getAllPersonInfo(String tableName) throws SQLException {
        return super.runGettingQuery(getPersonInfoStringQuery(tableName));
    }

    private String getPersonInfoStringQuery(String tableName) {
        return "select * from " + tableName + " ;";
    }

    @Override
    public int getPersonUIDByNameAndLastname(String name, String lastName) throws SQLException {
        ResultSet normalStudentUID = getPersonUIDByNameAndLastname(name, lastName, super.getAccess().getNormalStudentTable());
        int uid = getPersonUIDFromResultSet(normalStudentUID);
        if (uid != INVALID_UID) {
            return uid;
        }
        ResultSet workingStudentUID = getPersonUIDByNameAndLastname(name, lastName, super.getAccess().getWorkingStudentTable());
        uid = getPersonUIDFromResultSet(workingStudentUID);
        if (uid != INVALID_UID) {
            return uid;
        }
        ResultSet teacherUID = getPersonUIDByNameAndLastname(name, lastName, super.getAccess().getTeacherTable());
        uid = getPersonUIDFromResultSet(teacherUID);
        return uid;
    }

    private int getPersonUIDFromResultSet(ResultSet personUID) throws SQLException {
        if (super.isEmptyResultSet(personUID)) {
            return INVALID_UID;
        }
        return personUID.getInt("UID");
    }

    private ResultSet getPersonUIDByNameAndLastname(String name, String lastName, String tableName) throws SQLException {
        String query = getPersonUIDByNameAndLastnameQueryString(name, lastName, tableName);
        return super.runGettingQuery(query);
    }

    private String getPersonUIDByNameAndLastnameQueryString(String name, String lastName, String tableName) {
        System.out.println("UID Command : " + "select UID from " + tableName + " where name = '" + name + "' and last_name = '" + lastName + "' ;");
        return "select UID from " + tableName + " where name = '" + name + "' and last_name = '" + lastName + "' ;";
    }

    @Override
    public int getPersonUIDByPhoneNumber(String phoneNumber) throws SQLException {
        int normalStudentUID = getPersonUIDByPhoneNumber(super.getAccess().getNormalStudentTable(), phoneNumber);
        if (normalStudentUID != INVALID_UID) {
            return normalStudentUID;
        }
        int workingStudentUID = getPersonUIDByPhoneNumber(super.getAccess().getNormalStudentTable(), phoneNumber);
        if (workingStudentUID != INVALID_UID) {
            return workingStudentUID;
        }
        int teacherUID = getPersonUIDByPhoneNumber(super.getAccess().getNormalStudentTable(), phoneNumber);
        return teacherUID;
    }

    private int getPersonUIDByPhoneNumber(String tableName, String phoneNumber) throws SQLException {
        String query = getPersonUIDByPhoneNumberQueryString(tableName, phoneNumber);
        ResultSet personUID = super.runGettingQuery(query);
        return getPersonUIDFromResultSet(personUID);
    }

    private String getPersonUIDByPhoneNumberQueryString(String tableName, String phoneNumber) throws SQLException {
        return "select UID from " + tableName + " where phone_number = '" + phoneNumber + "' ;";
    }

}
