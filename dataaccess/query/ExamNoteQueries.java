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
public class ExamNoteQueries extends Query implements IExamNoteGettingQueries, IExamNoteSettingQueries, IExamNoteAddingQueries, IExamNoteRemovingQueries {

    private static ExamNoteQueries examQuery;

    static {
        examQuery = null;
    }

    private ExamNoteQueries() throws SQLException {

    }

    public static IExamNoteSettingQueries getInstanceForSettingQueries() throws SQLException {
        if (examQuery == null) {
            examQuery = new ExamNoteQueries();
        }
        return examQuery;
    }

    public static IExamNoteGettingQueries getInstanceForGettingQueries() throws SQLException {
        if (examQuery == null) {
            examQuery = new ExamNoteQueries();
        }
        return examQuery;
    }

    public static IExamNoteAddingQueries getInstanceForAddingQueries() throws SQLException {
        if (examQuery == null) {
            examQuery = new ExamNoteQueries();
        }
        return examQuery;
    }
    
    public static IExamNoteRemovingQueries getInstanceForRemovingQueries () throws SQLException {
        if (examQuery == null) {
            examQuery = new ExamNoteQueries () ;
        }
        return examQuery ;
    }

    @Override
    public void addNormalStudentToExamNoteTable(int studentUID, int lessonUID) throws SQLException {
        addStudentToExamNoteTable(studentUID, lessonUID, super.getAccess().getNormalStudentExamTable());
    }

    @Override
    public void addWorkingStudentToExamNoteTable(int studentUID, int lessonUID) throws SQLException {
        addStudentToExamNoteTable(studentUID, lessonUID, super.getAccess().getWorkingStudentExamTable());
    }

    private void addStudentToExamNoteTable(int studentUID, int lessonUID, String tableName) throws SQLException {
        String query = "insert into " + tableName + " values (" + studentUID + "," + lessonUID + ",0,0,0) ; ";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public void setMidtermNote(int studentUID, int lessonUID, int value) throws SQLException {
        String query = "update " + super.getAccess().getNormalStudentExamTable() + " set midterm = " + value + " where student_UID = " + studentUID + " and lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
        query = "update " + super.getAccess().getWorkingStudentExamTable() + " set midterm = " + value + " where student_UID = " + studentUID + " and lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public void setFinalNote(int studentUID, int lessonUID, int value) throws SQLException {
        String query = "update " + super.getAccess().getNormalStudentExamTable() + " set final = " + value + " where student_UID = " + studentUID + " and lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
        query = "update " + super.getAccess().getWorkingStudentExamTable() + " set final = " + value + " where student_UID = " + studentUID + " and lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public void setAverage(int studentUID, int lessonUID, int value) throws SQLException {
        String query = "update " + super.getAccess().getNormalStudentExamTable() + " set average = " + value + " where student_UID = " + studentUID + " and lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
        query = "update " + super.getAccess().getWorkingStudentExamTable() + " set average = " + value + " where student_UID = " + studentUID + " and lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public int getNormalStudentMidtermValues(int studentUID, int lessonUID) throws SQLException {
        return getStudentMidtermValue(studentUID, lessonUID, super.getAccess().getNormalStudentExamTable());
    }

    @Override
    public int getNormalStudentFinalValues(int studentUID, int lessonUID) throws SQLException {
        return getStudentFinalValue(studentUID, lessonUID, super.getAccess().getNormalStudentExamTable());
    }

    @Override
    public int getNormalStudentAverage(int studentUID, int lessonUID) throws SQLException {
        return getStudentAverage(studentUID, lessonUID, super.getAccess().getNormalStudentExamTable());
    }

    @Override
    public int getWorkingStudentMidtermValues(int studentUID, int lessonUID) throws SQLException {
        return getStudentMidtermValue(studentUID, lessonUID, super.getAccess().getWorkingStudentExamTable());
    }

    @Override
    public int getWorkingStudentFinalValues(int studentUID, int lessonUID) throws SQLException {
        return getStudentFinalValue(studentUID, lessonUID, super.getAccess().getWorkingStudentExamTable());
    }

    @Override
    public int getWorkingStudentAverage(int studentUID, int lessonUID) throws SQLException {
        return getStudentAverage(studentUID, lessonUID, super.getAccess().getWorkingStudentExamTable());
    }

    @Override
    public void removeNormalStudentFromExamTable(int studentUID) throws SQLException {
        removeStudentFromExamTable(studentUID, super.getAccess().getNormalStudentExamTable());
    }

    @Override
    public void removeWorkingStudentFromExamTable(int studentUID) throws SQLException {
        removeStudentFromExamTable(studentUID, super.getAccess().getWorkingStudentExamTable());
    }

    private void removeStudentFromExamTable(int studentUID, String column) throws SQLException {
        String query = "delete from " + column + " where student_UID = " + studentUID;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public void removeNormalStudentFromExamTable(int studentUID, int lessonUID) throws SQLException {
        removeStudentFromExamTable(studentUID, lessonUID, super.getAccess().getNormalStudentExamTable());
    }

    @Override
    public void removeWorkingStudentFromExamTable(int studentUID, int lessonUID) throws SQLException {
        removeStudentFromExamTable(studentUID, lessonUID, super.getAccess().getWorkingStudentExamTable());
    }
    
    private void removeStudentFromExamTable (int studentUID , int lessonUID , String column) throws SQLException{
        String query = "delete from " + column + " where student_UID = " + studentUID+" and lesson_UID = "+lessonUID;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    private int getStudentMidtermValue(int studentUID, int lessonUID, String tableName) throws SQLException {
        String query = "select midterm from " + tableName + " where student_UID = " + studentUID + " and lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int result = -1;
        while (resultSet.next()) {
            result = resultSet.getInt("midterm");
        }
        return result;
    }

    private int getStudentFinalValue(int studentUID, int lessonUID, String tableName) throws SQLException {
        String query = "select final from " + tableName + " where student_UID = " + studentUID + " and lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int result = -1;
        while (resultSet.next()) {
            result = resultSet.getInt("final");
        }
        return result;
    }

    private int getStudentAverage(int studentUID, int lessonUID, String tableName) throws SQLException {
        String query = "select average from " + tableName + " where student_UID = " + studentUID + " and lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int result = -1;
        while (resultSet.next()) {
            result = resultSet.getInt("average");
        }
        return result;
    }

}
