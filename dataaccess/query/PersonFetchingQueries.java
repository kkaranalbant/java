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
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeQuery();
    }

    @Override
    public ResultSet getWorkingStudentInfo(int uid) throws SQLException {
        String query = getWorkingStudentInfoQueryString(uid);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeQuery();
    }

    @Override
    public ResultSet getTeacherInfo(int uid) throws SQLException {
        String query = getTeacherInfoQueryString(uid);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeQuery();
    }

    @Override
    public int getPersonUIDByNameAndLastname(String name, String lastName) throws SQLException {
        ResultSet result1 = getPersonUIDByNameAndLastname(name, lastName, super.getAccess().getNormalStudentTable());
        if (result1.next()) {
            return result1.getInt("UID");
        }
        ResultSet result2 = getPersonUIDByNameAndLastname(name, lastName, super.getAccess().getWorkingStudentTable());
        if (result2.next()) {
            return result2.getInt("UID");
        }
        ResultSet result3 = getPersonUIDByNameAndLastname(name, lastName, super.getAccess().getTeacherTable());
        result3.next();
        return result3.getInt("UID");
    }

    @Override
    public ResultSet getAllNormalStudentInfo() throws SQLException {
        return getAllPersonInfo(super.getAccess().getNormalStudentTable()) ;
    }

    @Override
    public ResultSet getAllWorkingStudentInfo() throws SQLException {
        return getAllPersonInfo(super.getAccess().getWorkingStudentTable()) ;
    }

    @Override
    public ResultSet getAllTeacherInfo() throws SQLException {
        return getAllPersonInfo(super.getAccess().getTeacherTable()) ;
    }
    
    private ResultSet getAllPersonInfo (String tableName) throws SQLException{
        String query = "select * from "+tableName+" ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeQuery() ;
    }

    private ResultSet getPersonUIDByNameAndLastname(String name, String lastName, String tableName) throws SQLException {
        String query = "select UID from " + tableName + " where name = '" + name + "' and last_name = '" + lastName + "' ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeQuery();
    }

    private String getTeacherInfoQueryString(int uid) {
        return "select * from " + super.getAccess().getTeacherTable() + " where UID = " + uid;
    }

    private String getNormalStudentInfoQueryString(int uid) {
        return "select * from " + super.getAccess().getNormalStudentTable() + " where UID = " + uid;
    }

    private String getWorkingStudentInfoQueryString(int uid) {
        return "select * from " + super.getAccess().getWorkingStudentTable() + " where UID = " + uid;
    }
}
