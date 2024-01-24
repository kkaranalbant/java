/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
 *
 * @author kaan
 *
 */
public class PersonLoginQueries extends Query implements IPersonLoginQueries {

    private static IPersonLoginQueries normalStudentLoginQuery;

    private PersonLoginQueries() throws SQLException {
    }

    public static IPersonLoginQueries getInstance() throws SQLException {
        if (normalStudentLoginQuery == null) {
            normalStudentLoginQuery = new PersonLoginQueries();
        }
        return normalStudentLoginQuery;
    }

    @Override
    public ResultSet getAllNormalStudentUserNameAndPassword() throws SQLException {
        String query = getAllNormalStudentUserNameAndPasswordQueryString();
        return super.runGettingQuery(query);
    }

    private String getAllNormalStudentUserNameAndPasswordQueryString() {
        return "select * from " + super.getAccess().getNormalStudentLoginInfosTable();
    }

    @Override
    public ResultSet getAllWorkingStudentUserNameAndPassword() throws SQLException {
        String query = getAllWorkingStudentUserNameAndPasswordQueryString();
        return super.runGettingQuery(query);
    }

    private String getAllWorkingStudentUserNameAndPasswordQueryString() {
        return "select * from " + super.getAccess().getWorkingStudentLoginInfosTable();
    }

    @Override
    public ResultSet getAllTeacherUserNameAndPassword() throws SQLException {
        String query = getAllTeacherUserNameAndPasswordQueryString();
        return super.runGettingQuery(query);
    }

    private String getAllTeacherUserNameAndPasswordQueryString() {
        return "select * from " + super.getAccess().getTeacherLoginInfos();
    }

    @Override
    public String getNormalStudentUsernameByUID(int uid) throws SQLException {
        return getPersonUsernameByUID(uid, super.getAccess().getNormalStudentLoginInfosTable(), "normal_student_UID");
    }

    @Override
    public String getWorkingStudentUsernameByUID(int uid) throws SQLException {
        return getPersonUsernameByUID(uid, super.getAccess().getWorkingStudentLoginInfosTable(), "working_student_UID");
    }

    @Override
    public String getTeacherUsernameByUID(int uid) throws SQLException {
        return getPersonUsernameByUID(uid, super.getAccess().getTeacherLoginInfos(), "teacher_UID");
    }

    @Override
    public String getNormalStudentPassByUID(int uid) throws SQLException {
        return getPersonPassByUID(uid, super.getAccess().getNormalStudentLoginInfosTable(), "normal_student_UID");
    }

    @Override
    public String getWorkingStudentPassByUID(int uid) throws SQLException {
        return getPersonPassByUID(uid, super.getAccess().getWorkingStudentLoginInfosTable(), "working_student_UID");
    }

    @Override
    public String getTeacherPassByUID(int uid) throws SQLException {
        return getPersonPassByUID(uid, super.getAccess().getTeacherLoginInfos(), "teacher_UID");
    }

    private String getPersonUsernameByUID(int uid, String tableName, String columnName) throws SQLException {
        String query = getPersonUsernameByUIDQueryString(tableName, columnName, uid);
        return getPersonUsernameOrPass(query, "username");
    }

    private String getPersonUsernameByUIDQueryString(String tableName, String columnName, int uid) {
        return "SELECT username FROM " + tableName + " WHERE " + columnName + " = " + uid + " ;";
    }

    private String getPersonPassByUID(int uid, String tableName, String columnName) throws SQLException {
        String query = getPersonPassByUIDQueryString(tableName, columnName, uid);
        return getPersonUsernameOrPass(query, "pass");
    }

    private String getPersonUsernameOrPass(String query, String column) throws SQLException {
        ResultSet passOrUsernameOfPerson = super.runGettingQuery(query);
        String passOrUsername = null;
        if (!super.isEmptyResultSet(passOrUsernameOfPerson)) {
            passOrUsername = passOrUsernameOfPerson.getString(column);
        }
        return passOrUsername;
    }

    private String getPersonPassByUIDQueryString(String tableName, String columnName, int uid) {
        return "select pass from " + tableName + " where " + columnName + " = " + uid + " ;";
    }

    @Override
    public String getPersonUsernameByUID(int uid) throws SQLException {
        String normalStudentUsername = getPersonUsernameByUID(uid, super.getAccess().getNormalStudentLoginInfosTable(), "normal_student_UID");
        if (normalStudentUsername != null) {
            return normalStudentUsername;
        }
        String workingStudentUsername = getPersonUsernameByUID(uid, super.getAccess().getWorkingStudentLoginInfosTable(), "working_student_UID");
        if (workingStudentUsername != null) {
            return workingStudentUsername;
        }
        String teacherUsername = getPersonUsernameByUID(uid, super.getAccess().getTeacherLoginInfos(), "teacher_UID");
        return teacherUsername;
    }

}
