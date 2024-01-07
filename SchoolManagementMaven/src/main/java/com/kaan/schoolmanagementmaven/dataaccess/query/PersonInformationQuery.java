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
 * @author kaan
 */
public class PersonInformationQuery extends Query implements IStudentInformationQueries, ITeacherInformationQueries {

    private static PersonInformationQuery personInformationQuery;

    static {
        personInformationQuery = null;
    }

    private PersonInformationQuery() throws SQLException {

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
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeQuery();
    }

    private String getAllPersonNameLastnameAndUIDQueryString(String table) {
        return "select name , last_name , UID from " + table;
    }

    @Override
    public ResultSet getAllTeacherUIDForBranchID(int branchID) throws SQLException {
        String query = "select teacher_UID from " + super.getAccess().getTeacherBranchTable() + " where lesson_UID = " + branchID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeQuery();
    }

    @Override
    public List<String> getAllTeacherNameForBranchID(int branchID) throws SQLException {
        return getTeacherNameOrLastname("name", branchID);
    }

    @Override
    public List<String> getAllTeacherLastnameForBranchID(int branchID) throws SQLException {
        return getTeacherNameOrLastname("last_name", branchID);
    }

    private List<String> getTeacherNameOrLastname(String columnName, int branchID) throws SQLException {
        ResultSet resultSet = getAllTeacherUIDForBranchID(branchID);
        List<String> result = new ArrayList();
        while (resultSet.next()) {
            String query = "select " + columnName + " from " + super.getAccess().getTeacherTable() + " where UID = " + resultSet.getInt("teacher_UID");
            System.out.println(query);
            super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
            ResultSet resultForNameOrLastname = super.getPreparedStatement().executeQuery();
            while (resultForNameOrLastname.next()) {
                result.add(resultForNameOrLastname.getString(columnName));
            }
        }
        return result;
    }

}
