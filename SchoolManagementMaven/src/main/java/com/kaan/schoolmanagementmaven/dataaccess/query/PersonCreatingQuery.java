/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;

/**
 *
 * @author kaan
 */
public class PersonCreatingQuery extends Query implements IPersonCreatingQuery {

    private static IPersonCreatingQuery personCreatingQuery;
    private IDefaultValuesQuery defaultValues;
    private ILessonFetchingQuery lessonCreatingQuery;

    static {
        personCreatingQuery = null;
    }

    private PersonCreatingQuery() throws SQLException {
        defaultValues = DefaultValuesQuery.getInstance();
        lessonCreatingQuery = LessonFetchingQuery.getInstance();
    }

    public static IPersonCreatingQuery getInstance() throws SQLException {
        if (personCreatingQuery == null) {
            personCreatingQuery = new PersonCreatingQuery();
        }
        return personCreatingQuery;
    }

    @Override
    public void createNormalStudentInDb(String name, String lastname, String username, String pass, int uid, String phoneNumber) throws SQLException {
        createNormalStudentInDb(name, lastname, username, pass, uid, super.getAccess().getNormalStudentTable(),phoneNumber);
    }

    @Override
    public void createWorkingStudentIndDb(String name, String lastname, String username, String pass, int uid, String phoneNumber) throws SQLException {
        createWorkingStudentInDb(name, lastname, username, pass, uid, super.getAccess().getWorkingStudentTable(),phoneNumber);
    }

    @Override
    public void createTeacherInDb(String name, String lastname, String username, String pass, int uid, String branchName , int salary, String phoneNumber) throws SQLException {
        createTeacherInDb(name, lastname, username, pass, uid, branchName , salary , super.getAccess().getTeacherTable(),phoneNumber);
    }

    private void createNormalStudentInDb(String name, String lastname, String username, String pass, int uid, String tableName, String phoneNumber) throws SQLException {
        int defDebt = defaultValues.getDefaultDebt();
        int defBalance = defaultValues.getDefaultBalance();
        int defLessonCredit = defaultValues.getDefaultLessonCredit();
        int defMaxDebtCredit = defaultValues.getDefaultMaxDebtCredit();
        String query1 = getStudentCreatingInDbQueryString(tableName, name, lastname, uid, defDebt, defBalance, defLessonCredit, defMaxDebtCredit,phoneNumber);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query1));
        super.getPreparedStatement().executeUpdate();
        String query2 = createLoginInfoInDbQueryString(super.getAccess().getNormalStudentLoginInfosTable(), username, pass, uid);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query2));
        super.getPreparedStatement().executeUpdate();
    }

    private void createWorkingStudentInDb(String name, String lastname, String username, String pass, int uid, String tableName, String phoneNumber) throws SQLException {
        int defDebt = defaultValues.getDefaultDebt();
        int defBalance = defaultValues.getDefaultBalance();
        int defLessonCredit = defaultValues.getDefaultLessonCredit();
        int defMaxDebtCredit = defaultValues.getDefaultMaxDebtCredit();
        String query1 = getStudentCreatingInDbQueryString(tableName, name, lastname, uid, defDebt, defBalance, defLessonCredit, defMaxDebtCredit,phoneNumber);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query1));
        super.getPreparedStatement().executeUpdate();
        String query2 = createLoginInfoInDbQueryString(super.getAccess().getWorkingStudentLoginInfosTable(), username, pass, uid);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query2));
        super.getPreparedStatement().executeUpdate();
    }

    private void createTeacherInDb(String name, String lastname, String username, String pass, int uid, String branchName, int salary, String tableName, String phoneNumber) throws SQLException {
        int defBalance = defaultValues.getDefaultBalance();
        String query1 = getTeacherCreatingInDbQueryString(tableName, name, lastname, uid, defBalance , salary , phoneNumber);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query1));
        super.getPreparedStatement().executeUpdate();
        int branchId = lessonCreatingQuery.getLessonUIDByLessonName(branchName);
        String query2 = createTeacherBranchInDb(uid, branchId, super.getAccess().getTeacherBranchTable());
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query2));
        super.getPreparedStatement().executeUpdate();
        String query3 = createLoginInfoInDbQueryString(super.getAccess().getTeacherLoginInfos(), username, pass, uid);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query3));
        super.getPreparedStatement().executeUpdate();

    }

    private String getStudentCreatingInDbQueryString(String tableName, String name, String lastname, int uid, int defDebt, int defBalance, int defLessonCredit, int defMaxDebtCredit , String phoneNumber) {
        return "insert into " + tableName + " values ('" + name + "','" + lastname + "'," + uid + "," + defBalance + "," + defDebt + "," + defLessonCredit +",'" +phoneNumber+"');";
    }

    private String createLoginInfoInDbQueryString(String tableName, String userName, String pass, int uid) {
        return "insert into " + tableName + " values (" + uid + ",'" + userName + "','" + pass + "') ;";
    }

    private String getTeacherCreatingInDbQueryString(String tableName, String name, String lastName, int uid, int defBalance, int salary, String phoneNumber) {
        return "insert into " + tableName + " values ('" + name + "','" + lastName + "'," + uid + "," + defBalance + ", " + salary + ",'" +phoneNumber+"');";
    }

    private String createTeacherBranchInDb(int uid, int branchId, String tableName) {
        return "insert into " + tableName + " values (" + uid + "," + branchId + ") ; ";
    }
}
