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

    public static IExamNoteRemovingQueries getInstanceForRemovingQueries() throws SQLException {
        if (examQuery == null) {
            examQuery = new ExamNoteQueries();
        }
        return examQuery;
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
        String query = getAddingStudentToExamTableStringQuery(tableName, studentUID, lessonUID);
        super.runUpdatingQuery(query);
    }

    private String getAddingStudentToExamTableStringQuery(String tableName, int studentUID, int lessonUID) {
        return "insert into " + tableName + " values (" + studentUID + "," + lessonUID + ",0,0,0) ; ";
    }

    @Override
    public void setMidtermNote(int studentUID, int lessonUID, int value) throws SQLException {
        setNote(studentUID, lessonUID, value, "midterm");

    }

    @Override
    public void setFinalNote(int studentUID, int lessonUID, int value) throws SQLException {
        setNote(studentUID, lessonUID, value, "final");

    }

    @Override
    public void setAverage(int studentUID, int lessonUID, int value) throws SQLException {
        setNote(studentUID, lessonUID, value, "average");
    }

    private void setNote(int studentUID, int lessonUID, int value, String noteColumn) throws SQLException {
        boolean isNormalStudentTableEffected = setNote(super.getAccess().getNormalStudentExamTable(), noteColumn, studentUID, lessonUID, value);
        if (isNormalStudentTableEffected) {
            return;
        }
        setNote(super.getAccess().getWorkingStudentExamTable(), noteColumn, studentUID, lessonUID, value);
    }

    private boolean setNote(String tableName, String column, int studentUID, int lessonUID, int value) throws SQLException {
        String query = getNoteSettingQuery(tableName, column, studentUID, lessonUID, value);
        super.runUpdatingQuery(query);
        int effectedRow = super.runUpdatingQuery(query);
        if (effectedRow != 0) {
            return true;
        }
        return false;
    }

    private String getNoteSettingQuery(String examTableName, String column, int studentUID, int lessonUID, int value) {
        return "update " + examTableName + " set " + column + " = "+value+" where student_UID = " + studentUID + " and lesson_UID = " + lessonUID + " ;";
    }

    @Override
    public int getNormalStudentMidtermValues(int studentUID, int lessonUID) throws SQLException {
        return getStudentNote(studentUID, lessonUID, super.getAccess().getNormalStudentExamTable(), "midterm");
    }

    @Override
    public int getNormalStudentFinalValues(int studentUID, int lessonUID) throws SQLException {
        return getStudentNote(studentUID, lessonUID, super.getAccess().getNormalStudentExamTable(), "final");
    }

    @Override
    public int getNormalStudentAverage(int studentUID, int lessonUID) throws SQLException {
        return getStudentNote(studentUID, lessonUID, super.getAccess().getNormalStudentExamTable(), "average");
    }

    @Override
    public int getWorkingStudentMidtermValues(int studentUID, int lessonUID) throws SQLException {
        return getStudentNote(studentUID, lessonUID, super.getAccess().getWorkingStudentExamTable(), "midterm");
    }

    @Override
    public int getWorkingStudentFinalValues(int studentUID, int lessonUID) throws SQLException {
        return getStudentNote(studentUID, lessonUID, super.getAccess().getWorkingStudentExamTable(), "final");
    }

    @Override
    public int getWorkingStudentAverage(int studentUID, int lessonUID) throws SQLException {
        return getStudentNote(studentUID, lessonUID, super.getAccess().getWorkingStudentExamTable(), "average");
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
        String query = getRemoveStudentFromExamTableQueryString(studentUID, column);
        super.runUpdatingQuery(query);
    }

    private String getRemoveStudentFromExamTableQueryString(int studentUID, String column) {
        return "delete from " + column + " where student_UID = " + studentUID;
    }

    @Override
    public void removeNormalStudentFromExamTable(int studentUID, int lessonUID) throws SQLException {
        removeStudentFromExamTable(studentUID, lessonUID, super.getAccess().getNormalStudentExamTable());
    }

    @Override
    public void removeWorkingStudentFromExamTable(int studentUID, int lessonUID) throws SQLException {
        removeStudentFromExamTable(studentUID, lessonUID, super.getAccess().getWorkingStudentExamTable());
    }

    private void removeStudentFromExamTable(int studentUID, int lessonUID, String column) throws SQLException {
        String query = getStudentRemovingFromExamTableQueryString(studentUID, lessonUID, column);
        super.runUpdatingQuery(query);
    }

    private String getStudentRemovingFromExamTableQueryString(int studentUID, int lessonUID, String column) {
        return "delete from " + column + " where student_UID = " + studentUID + " and lesson_UID = " + lessonUID;
    }

    private int getStudentNote(int studentUID, int lessonUID, String tableName, String noteColumn) throws SQLException {
        String query = getStudentNoteQuery(tableName, studentUID, lessonUID, noteColumn);
        ResultSet studentNote = super.runGettingQuery(query);
        if(super.isEmptyResultSet(studentNote)) {
            return -1 ;
        }
        return studentNote.getInt(noteColumn);
    }

    private String getStudentNoteQuery(String tableName, int studentUID, int lessonUID, String noteColumn) {
        return "select " + noteColumn + " from " + tableName + " where student_UID = " + studentUID + " and lesson_UID = " + lessonUID + " ;";
    }

}
