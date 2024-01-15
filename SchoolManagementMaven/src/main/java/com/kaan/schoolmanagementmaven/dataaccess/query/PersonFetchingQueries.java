/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.kaan.schoolmanagementmaven.lesson.Lesson;

/**
 *
 * Ogrencilerin giris yaptiktan sonra nesnelerinin olusabilmesi icin
 * veritabanindan gerekli bilgileri almaya yarayacak sinif .
 *
 * @author kaan
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
        String query = getPersonInfoStringQuery(tableName);
        return super.runGettingQuery(query);
    }

    private String getPersonInfoStringQuery(String tableName) {
        return "select * from " + tableName + " ;";
    }

    @Override
    public int getPersonUIDByNameAndLastname(String name, String lastName) throws SQLException {
        ResultSet normalStudentUID = getPersonUIDByNameAndLastname(name, lastName, super.getAccess().getNormalStudentTable());
        if (isEmptyResultSet(normalStudentUID)) {
            return getPersonUIDFromResultSet(normalStudentUID);
        }
        ResultSet workingStudentUID = getPersonUIDByNameAndLastname(name, lastName, super.getAccess().getWorkingStudentTable());
        if (isEmptyResultSet(workingStudentUID)) {
            return getPersonUIDFromResultSet(workingStudentUID);
        }
        ResultSet teacherUID = getPersonUIDByNameAndLastname(name, lastName, super.getAccess().getTeacherTable());
        if (isEmptyResultSet(teacherUID)) {
            return getPersonUIDFromResultSet(teacherUID);
        }
        return INVALID_UID;
    }

    private int getPersonUIDFromResultSet(ResultSet personUID) throws SQLException {
        if (isEmptyResultSet(personUID)) {
            return INVALID_UID;
        }
        personUID.next();
        return personUID.getInt("UID");
    }

    private boolean isEmptyResultSet(ResultSet resultSet) throws SQLException {
        return !resultSet.next();
    }

    private ResultSet getPersonUIDByNameAndLastname(String name, String lastName, String tableName) throws SQLException {
        String query = getPersonUIDByNameAndLastnameQueryString(name, lastName, tableName);
        return super.runGettingQuery(query);
    }

    private String getPersonUIDByNameAndLastnameQueryString(String name, String lastName, String tableName) {
        return "select UID from " + tableName + " where name = '" + name + "' and last_name = '" + lastName + "' ;";
    }

    @Override
    public int getPersonUIDByPhoneNumber(String tableName, String phoneNumber) throws SQLException {
        String query = getPersonUIDByPhoneNumberQueryString(tableName, phoneNumber);
        ResultSet personUID = super.runGettingQuery(query);
        return getPersonUIDFromResultSet(personUID);
    }

    private String getPersonUIDByPhoneNumberQueryString(String tableName, String phoneNumber) throws SQLException {
        return "select UID from " + tableName + " where phone_number = '" + phoneNumber + "' ;";
    }

}
