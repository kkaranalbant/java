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
public class PersonValueChangingQueries extends Query implements IStudentValueChangingQueries, ITeacherValueChangingQueries {

    private static PersonValueChangingQueries valueChangingObject;
    private IPersonFetchingQueries personFetcher;

    static {
        valueChangingObject = null;
    }

    private PersonValueChangingQueries() throws SQLException {
        personFetcher = PersonFetchingQueries.getInstance();
    }

    public static IStudentValueChangingQueries getInstanceForStudent() throws SQLException {
        if (valueChangingObject == null) {
            valueChangingObject = new PersonValueChangingQueries();
        }
        return valueChangingObject;
    }

    public static ITeacherValueChangingQueries getInstanceForTeacher() throws SQLException {
        if (valueChangingObject == null) {
            valueChangingObject = new PersonValueChangingQueries();
        }
        return valueChangingObject;
    }

    @Override
    public void changeNormalStudentName(int uid, String newName) throws SQLException {
        changePersonName(uid, newName, super.getAccess().getNormalStudentTable());
    }

    @Override
    public void changeNormalStudentLastname(int uid, String newLastame) throws SQLException {
        changePersonLastname(uid, newLastame, super.getAccess().getNormalStudentTable());
    }

    @Override
    public void changeWorkingStudentName(int uid, String newName) throws SQLException {
        changePersonName(uid, newName, super.getAccess().getWorkingStudentTable());
    }

    @Override
    public void changeWorkingStudentLastname(int uid, String newLastname) throws SQLException {
        changePersonLastname(uid, newLastname, super.getAccess().getWorkingStudentTable());
    }

    @Override
    public void changeTeacherName(int uid, String newName) throws SQLException {
        changePersonName(uid, newName, super.getAccess().getTeacherTable());
    }

    @Override
    public void changeTeacherLastname(int uid, String newLastname) throws SQLException {
        changePersonLastname(uid, newLastname, super.getAccess().getTeacherTable());
    }

    @Override
    public void changeNormalStudentBalance(int uid, int newBalance) throws SQLException {
        changePersonBalance(uid, newBalance, super.getAccess().getNormalStudentTable());
    }

    @Override
    public void changeWorkingStudentBalance(int uid, int newBalance) throws SQLException {
        changePersonBalance(uid, newBalance, super.getAccess().getWorkingStudentTable());
    }

    @Override
    public void changeTeacherBalance(int uid, int newBalance) throws SQLException {
        changePersonBalance(uid, newBalance, super.getAccess().getTeacherTable());
    }

    @Override
    public void changeNormalStudentUsername(int uid, String newUserName) throws SQLException {
        changePersonUsername(uid, newUserName, super.getAccess().getNormalStudentLoginInfosTable(), "normal_student_UID");
    }

    @Override
    public void changeWorkingStudentUsername(int uid, String newUserName) throws SQLException {
        changePersonUsername(uid, newUserName, super.getAccess().getWorkingStudentLoginInfosTable(), "working_student_UID");
    }

    @Override
    public void changeTeacherUsername(int uid, String newUserName) throws SQLException {
        changePersonUsername(uid, newUserName, super.getAccess().getTeacherLoginInfos(), "teacher_UID");

    }

    @Override
    public void changeNormalStudentPass(int uid, String newPass) throws SQLException {
        changePersonPass(uid, newPass, super.getAccess().getNormalStudentLoginInfosTable(), "normal_student_UID");
    }

    @Override
    public void changeWorkingStudentPass(int uid, String newPass) throws SQLException {
        changePersonPass(uid, newPass, super.getAccess().getWorkingStudentLoginInfosTable(), "working_student_UID");
    }

    @Override
    public void changeTeacherPass(int uid, String newPass) throws SQLException {
        changePersonPass(uid, newPass, super.getAccess().getTeacherLoginInfos(), "teacher_UID");
    }

    @Override
    public void changeNormalStudentDebt(int uid, int value) throws SQLException {
        setStudentDebt(uid, value, super.getAccess().getNormalStudentTable());
    }

    @Override
    public void changeWorkingStudentDebt(int uid, int value) throws SQLException {
        setStudentDebt(uid, value, super.getAccess().getWorkingStudentTable());
    }

    @Override
    public void changeNormalStudentLessonCredit(int uid, int value) throws SQLException {
        setStudentLessonCredit(uid, value, super.getAccess().getNormalStudentTable(), "student_lesson_credit");
    }

    @Override
    public void changeWorkingStudentLessonCredit(int uid, int value) throws SQLException {
        setStudentLessonCredit(uid, value, super.getAccess().getWorkingStudentTable(), "student_lesson_credit");
    }

    @Override
    public void setTeacherSalary(int uid, int value) throws SQLException {
        String query = getchangingPersonIntegerAttributeQueryString(super.getAccess().getTeacherTable(), "salary", value, uid);
        super.runUpdatingQuery(query);
    }

    @Override
    public void setTeacherBranch(int uid, int branchId) throws SQLException {
        String query = getTeacherBranchSettingQueryString(branchId, uid);
        super.runUpdatingQuery(query);
    }

    private String getTeacherBranchSettingQueryString(int branchId, int teacherUID) {
        return "update " + super.getAccess().getTeacherBranchTable() + " set lesson_UID = " + branchId + " where teacher_UID = " + teacherUID + " ; ";
    }

    private void setStudentDebt(int uid, int value, String tableName) throws SQLException {
        String query = getchangingPersonIntegerAttributeQueryString(tableName, "debt", value, uid);
        super.runUpdatingQuery(query);
    }

    private void setStudentLessonCredit(int uid, int value, String tableName, String columnName) throws SQLException {
        String query = getchangingPersonIntegerAttributeQueryString(tableName, columnName, value, uid);
        super.runUpdatingQuery(query);
    }

    @Override
    public void changeNormalStudentPhoneNumber(int uid, String newPhoneNumber) throws SQLException {
        changePersonPhoneNumber(uid, newPhoneNumber, super.getAccess().getNormalStudentTable());
    }

    @Override
    public void changeWorkingStudentPhoneNumber(int uid, String newPhoneNumber) throws SQLException {
        changePersonPhoneNumber(uid, newPhoneNumber, super.getAccess().getWorkingStudentTable());
    }

    @Override
    public void changeTeacherPhoneNumber(int uid, String newPhoneNumber) throws SQLException {
        changePersonPhoneNumber(uid, newPhoneNumber, super.getAccess().getTeacherTable());
    }

    private void changePersonPhoneNumber(int uid, String newPhoneNumber, String tableName) throws SQLException {
        String query = getchangingPersonStringAttributeQueryString(tableName, "phone_number", newPhoneNumber, uid);
        super.runUpdatingQuery(query);
    }

    private void changePersonName(int uid, String newName, String tableName) throws SQLException {
        String query = getchangingPersonStringAttributeQueryString(tableName, "name", newName, uid);
        super.runUpdatingQuery(query);
    }

    private void changePersonLastname(int uid, String newLastname, String tableName) throws SQLException {
        String query = getchangingPersonStringAttributeQueryString(tableName, "last_name", newLastname, uid);
        super.runUpdatingQuery(query);
    }

    private void changePersonBalance(int uid, int newBalance, String tableName) throws SQLException {
        String query = getchangingPersonIntegerAttributeQueryString(tableName, "balance", newBalance, uid);
        super.runUpdatingQuery(query);
    }

    private void changePersonUsername(int uid, String newUserName, String tableName, String columnName) throws SQLException {
        String query = getChangingPersonLoginInfoQueryString(tableName, "username", newUserName, columnName, uid);
        super.runUpdatingQuery(query);
    }

    private void changePersonPass(int uid, String newPass, String tableName, String columnName) throws SQLException {
        String query = getChangingPersonLoginInfoQueryString(tableName, "pass", newPass, columnName, uid);
        super.runUpdatingQuery(query);
    }

    private String getchangingPersonIntegerAttributeQueryString(String tableName, String attributeName, int newValue, int uid) {
        return "update " + tableName + " set " + attributeName + " = " + newValue + " where UID = " + uid + " ;";
    }

    private String getchangingPersonStringAttributeQueryString(String tableName, String attributeName, String newValue, int uid) {
        return "update " + tableName + " set " + attributeName + " = '" + newValue + "' where UID = " + uid + " ;";
    }

    private String getChangingPersonLoginInfoQueryString(String tableName, String columnName, String newValue, String uidColumnName, int uid) {
        return "update " + tableName + " set " + columnName + " = '" + newValue + "' where " + uidColumnName + " = " + uid + " ;";
    }

    @Override
    public void changeNormalStudentPassWithPhoneNumber(String phoneNumber, String newPass) throws SQLException {
        changePersonPassWithPhoneNumber(phoneNumber, newPass, super.getAccess().getNormalStudentLoginInfosTable(), "normal_student_UID");
    }

    @Override
    public void changeWorkingStudentPassWithPhoneNumber(String phoneNumber, String newPass) throws SQLException {
        changePersonPassWithPhoneNumber(phoneNumber, newPass, super.getAccess().getWorkingStudentLoginInfosTable(), "working_student_UID");
    }

    @Override
    public void changeTeacherPassWithPhoneNumber(String phoneNumber, String newPass) throws SQLException {
        changePersonPassWithPhoneNumber(phoneNumber, newPass, super.getAccess().getTeacherLoginInfos(), "teacher_UID");
    }

    private void changePersonPassWithPhoneNumber(String phoneNumber, String newPass, String tableNameForPass, String columnName) throws SQLException {
        int uid = personFetcher.getPersonUIDByPhoneNumber(phoneNumber);
        String query = getChangingPersonLoginInfoQueryString(tableNameForPass, "pass", newPass, columnName, uid);
        super.runUpdatingQuery(query);
    }

}
