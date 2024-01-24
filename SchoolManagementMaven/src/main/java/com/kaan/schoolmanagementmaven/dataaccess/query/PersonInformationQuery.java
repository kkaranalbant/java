/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 * @author kaan
 */
public class PersonInformationQuery extends Query implements IStudentInformationQueries, ITeacherInformationQueries {

    private static PersonInformationQuery personInformationQuery;
    private IPersonFetchingQueries personFetcher;

    static {
        personInformationQuery = null;
    }

    private PersonInformationQuery() throws SQLException {
        personFetcher = PersonFetchingQueries.getInstance();
    }

    public static IStudentInformationQueries getInstanceForStudent() throws SQLException {
        if (personInformationQuery == null) {
            personInformationQuery = new PersonInformationQuery();
        }
        return personInformationQuery;
    }

    public static ITeacherInformationQueries getInstanceForTeacher() throws SQLException {
        if (personInformationQuery == null) {
            personInformationQuery = new PersonInformationQuery();
        }
        return personInformationQuery;
    }

    @Override
    public ResultSet getAllNormalStudentNameLastnameAndUID() throws SQLException {
        return getAllPersonNameLastnameAndUID(super.getAccess().getNormalStudentTable());
    }

    @Override
    public ResultSet getAllWorkingStudentNameLastnameAndUID() throws SQLException {
        return getAllPersonNameLastnameAndUID(super.getAccess().getWorkingStudentTable());
    }

    @Override
    public ResultSet getAllTeacherNameLastnameAndUID() throws SQLException {
        return getAllPersonNameLastnameAndUID(super.getAccess().getTeacherTable());
    }

    private ResultSet getAllPersonNameLastnameAndUID(String tableName) throws SQLException {
        String query = getAllPersonNameLastnameAndUIDQueryString(tableName);
        return super.runGettingQuery(query);
    }

    private String getAllPersonNameLastnameAndUIDQueryString(String table) {
        return "select name , last_name , UID from " + table;
    }

    @Override
    public ResultSet getAllTeacherUIDWith(int branchID) throws SQLException {
        String query = getAllTeacherUIDQueryString(branchID);
        return super.runGettingQuery(query);
    }

    private String getAllTeacherUIDQueryString(int branchUID) throws SQLException {
        return "select teacher_UID from " + super.getAccess().getTeacherBranchTable() + " where lesson_UID = " + branchUID + " ;";
    }

    @Override
    public int getBranchId(int teacherUID) throws SQLException {
        String query = getBranchUIDQueryString(teacherUID);
        ResultSet branchUID = super.runGettingQuery(query);
        branchUID.next();
        return branchUID.getInt("lesson_UID");
    }

    private String getBranchUIDQueryString(int teacherUID) {
        return "select lesson_UID from " + super.getAccess().getTeacherBranchTable() + " where teacher_UID = " + teacherUID + " ;";
    }

    @Override
    public List<String> getAllTeacherNameWith(int branchID) throws SQLException {
        List <String> teacherNames = new ArrayList () ;
        addTeacherNameOrLastnameList("name", branchID, teacherNames);
        return teacherNames ;
    }

    @Override
    public List<String> getAllTeacherLastnameWith(int branchID) throws SQLException {
        List <String> teacherLastnames = new ArrayList () ;
        addTeacherNameOrLastnameList("last_name", branchID, teacherLastnames);
        return teacherLastnames ;
    }

    private void addTeacherNameOrLastnameList(String columnName, int branchID, List<String> teacherNamesOrLastnames) throws SQLException {
        ResultSet teachers = getAllTeacherUIDWith(branchID);
        while (teachers.next()) {
            int teacherUID = teachers.getInt("teacher_UID");
            ResultSet teacher = personFetcher.getTeacherInfo(teacherUID);
            String nameOrLastname = getTeacherNameOrLastName(columnName, teacher);
            teacherNamesOrLastnames.add(nameOrLastname);
        }
    }

    private String getTeacherNameOrLastName(String columnName, ResultSet teacher) throws SQLException {
        String nameOrLastname = null ;
        while (teacher.next()) {
            nameOrLastname = teacher.getString(columnName);
        }
        return nameOrLastname ;
    }

}
