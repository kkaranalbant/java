/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;

/**
 *
 * @author kaan
 * 
 */
public class PersonCreatingQuery extends Query implements IPersonCreatingQuery , IPersonCreatingQueryForConverting {

    private static IPersonCreatingQuery personCreatingQuery;
    private IDefaultValuesQuery defaultValues;
    private ILessonFetchingQuery lessonCreatingQuery;
    private int defDebt;
    private int defBalance;
    private int defLessonCredit;
    private int defMaxDebtCredit;

    static {
        personCreatingQuery = null;
    }

    private PersonCreatingQuery() throws SQLException {
        defaultValues = DefaultValuesQuery.getInstance();
        lessonCreatingQuery = LessonFetchingQuery.getInstance();
        defDebt = defaultValues.getDefaultDebt();
        defBalance = defaultValues.getDefaultBalance();
        defLessonCredit = defaultValues.getDefaultLessonCredit();
        defMaxDebtCredit = defaultValues.getDefaultMaxDebtCredit();
    }

    public static IPersonCreatingQuery getInstance() throws SQLException {
        if (personCreatingQuery == null) {
            personCreatingQuery = new PersonCreatingQuery();
        }
        return personCreatingQuery;
    }
    
    static IPersonCreatingQueryForConverting getInstanceForConverting () throws SQLException {
        return (IPersonCreatingQueryForConverting) getInstance() ;
    }

    @Override
    public void createNormalStudentInDb(String name, String lastname, String username, String pass, int uid, String phoneNumber) throws SQLException {
        createStudentInfoInDb(super.getAccess().getNormalStudentTable(), name, lastname, uid, phoneNumber);
        createPersonLoginInfoInDb(username, pass, uid, super.getAccess().getNormalStudentLoginInfosTable());
    }

    @Override
    public void createWorkingStudentIndDb(String name, String lastname, String username, String pass, int uid, String phoneNumber) throws SQLException {
        createStudentInfoInDb(super.getAccess().getWorkingStudentTable(), name, lastname, uid, phoneNumber);
        createPersonLoginInfoInDb(username, pass, uid, super.getAccess().getWorkingStudentLoginInfosTable());
    }

    @Override
    public void createTeacherInDb(String name, String lastname, String username, String pass, int uid, String branchName, int salary, String phoneNumber) throws SQLException {
        createTeacherInfoInDb(name, lastname, uid, salary, phoneNumber);
        createPersonLoginInfoInDb(username, pass, uid, super.getAccess().getTeacherLoginInfos());
        createTeacherBranchInDb(uid, branchName);
    }

    @Override
    public void createStudentInfoInDb(String studentTableName, String name, String lastname, int uid, String phoneNumber) throws SQLException {
        String studentCreatingQuery = getStudentCreatingInDbQueryString(studentTableName, name, lastname, uid, defDebt, defBalance, defLessonCredit, defMaxDebtCredit, phoneNumber);
        super.runUpdatingQuery(studentCreatingQuery);
    }

    @Override
    public void createPersonLoginInfoInDb(String userName, String pass, int uid, String studentLoginInfoTableName) throws SQLException {
        String studentLoginInfoCreatingQuery = createLoginInfoInDbQueryString(studentLoginInfoTableName, userName, pass, uid);
        super.runUpdatingQuery(studentLoginInfoCreatingQuery);
    }

    private void createTeacherInfoInDb(String name, String lastname, int uid, int salary, String phoneNumber) throws SQLException {
        String teacherCreatingQuery = getTeacherCreatingInDbQueryString(super.getAccess().getTeacherTable(), name, lastname, uid, defBalance, salary, phoneNumber);
        super.runUpdatingQuery(teacherCreatingQuery);
    }

    private void createTeacherBranchInDb(int teacherUID, String branchName) throws SQLException {
        int branchId = lessonCreatingQuery.getLessonUIDByLessonName(branchName);
        String branchCreatingQuery = createTeacherBranchInDb(teacherUID, branchId, super.getAccess().getTeacherBranchTable());
        super.runUpdatingQuery(branchCreatingQuery);
    }

    private String getStudentCreatingInDbQueryString(String tableName, String name, String lastname, int uid, int defDebt, int defBalance, int defLessonCredit, int defMaxDebtCredit, String phoneNumber) {
        return "insert into " + tableName + " values ('" + name + "','" + lastname + "'," + uid + "," + defBalance + "," + defDebt + "," + defLessonCredit + ",'" + phoneNumber + "');";
    }

    private String createLoginInfoInDbQueryString(String tableName, String userName, String pass, int uid) {
        return "insert into " + tableName + " values (" + uid + ",'"+userName+"','"+pass+"') ;";
    }

    private String getTeacherCreatingInDbQueryString(String tableName, String name, String lastName, int uid, int defBalance, int salary, String phoneNumber) {
        return "insert into " + tableName + " values ('" + name + "','" + lastName + "'," + uid + "," + defBalance + ", " + salary + ",'" + phoneNumber + "');";
    }

    private String createTeacherBranchInDb(int uid, int branchId, String tableName) {
        return "insert into " + tableName + " values (" + uid + "," + branchId + ") ; ";
    }
}
