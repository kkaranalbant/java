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
 */
public class PersonValueChangingQueries extends Query implements IStudentValueChangingQueries, ITeacherValueChangingQueries {

    private static PersonValueChangingQueries valueChangingObject;

    static {
        valueChangingObject = null;
    }

    private PersonValueChangingQueries() throws SQLException {
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
    public void setNormalStudentDebt(int uid, int value) throws SQLException {
        setStudentDebt(uid, value, super.getAccess().getNormalStudentTable());
    }

    @Override
    public void setWorkingStudentDebt(int uid, int value) throws SQLException {
        setStudentDebt(uid, value, super.getAccess().getWorkingStudentTable());
    }

    @Override
    public void setNormalStudentLessonCredit(int uid, int value) throws SQLException {
        setStudentLessonCredit(uid, value, super.getAccess().getNormalStudentTable(), "student_lesson_credit");
    }

    @Override
    public void setWorkingStudentLessonCredit(int uid, int value) throws SQLException {
        setStudentLessonCredit(uid, value, super.getAccess().getWorkingStudentTable(), "lesson_credit");
    }

    @Override
    public void setTeacherSalary(int uid, int value) throws SQLException {
        String query = "update " + super.getAccess().getTeacherTable() + " set salary = " + value + " where UID = " + uid + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public void setTeacherBranch(int uid, int branchId) throws SQLException {
        String query = "update " + super.getAccess().getTeacherBranchTable() + " set lesson_UID = " + branchId + " where teacher_UID = " + uid + " ; ";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    private void setStudentDebt(int uid, int value, String tableName) throws SQLException {
        String query = "update " + tableName + " set debt = " + value + " where UID = " + uid + " ; ";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    private void setStudentLessonCredit(int uid, int value, String tableName, String columnName) throws SQLException {
        String query = "update " + tableName + " set " + columnName + " = " + value + " where UID = " + uid + " ; ";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
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
        String query = "update " + tableName + " set phone_number = '" + newPhoneNumber + "' where UID = " + uid + ";";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    private void changePersonName(int uid, String newName, String tableName) throws SQLException {
        String query = "update " + tableName + " set name = '" + newName + "' where UID = " + uid;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    private void changePersonLastname(int uid, String newName, String tableName) throws SQLException {
        String query = "update " + tableName + " set last_name = '" + newName + "' where UID = " + uid;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    private void changePersonBalance(int uid, int newBalance, String tableName) throws SQLException {
        String query = "update " + tableName + " set balance = " + newBalance + " where UID = " + uid;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    private void changePersonUsername(int uid, String newUserName, String tableName, String columnName) throws SQLException {
        String query = "update " + tableName + " set username = '" + newUserName + "' where " + columnName + " = " + uid;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    private void changePersonPass(int uid, String newPass, String tableName, String columnName) throws SQLException {
        String query = "update " + tableName + " set pass = '" + newPass + "' where " + columnName + " = " + uid;
        System.out.println(query);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public void changeNormalStudentPassWithPhoneNumber(String phoneNumber, String newPass) throws SQLException {
        changePersonPassWithPhoneNumber(phoneNumber, newPass, super.getAccess().getNormalStudentTable(), super.getAccess().getNormalStudentLoginInfosTable(), "normal_student_UID");
    }

    @Override
    public void changeWorkingStudentPassWithPhoneNumber(String phoneNumber, String newPass) throws SQLException {
        changePersonPassWithPhoneNumber(phoneNumber, newPass, super.getAccess().getWorkingStudentTable(), super.getAccess().getWorkingStudentLoginInfosTable(), "working_student_UID");
    }

    @Override
    public void changeTeacherPassWithPhoneNumber(String phoneNumber, String newPass) throws SQLException {
        changePersonPassWithPhoneNumber(phoneNumber, newPass, super.getAccess().getTeacherTable(), super.getAccess().getTeacherLoginInfos(), "teacher_UID");
    }

    private void changePersonPassWithPhoneNumber(String phoneNumber, String newPass, String tableNameForUID , String tableNameForPass , String columnName) throws SQLException {
        String query = "select UID from " + tableNameForUID + " where phone_number = '" + phoneNumber+"' ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery() ;
        int uid = 0 ;
        while (resultSet.next()) {
            uid = resultSet.getInt("UID") ;
        }
        query = "update "+tableNameForPass+" set pass = '"+newPass+"' where "+columnName+" = "+uid ;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate() ;
    }

}
