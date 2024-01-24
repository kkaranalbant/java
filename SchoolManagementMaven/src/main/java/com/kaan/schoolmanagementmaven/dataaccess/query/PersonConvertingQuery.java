/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author kaan
 *
 */
public class PersonConvertingQuery extends Query implements IPersonConvertingQuery {

    private static IPersonConvertingQuery personConvertingQuery;
    private IPersonDeletingQuery personDeletor;
    private IPersonCreatingQueryForConverting personCreator;
    private IExamNoteGettingQueries examGetter;

    static {
        personConvertingQuery = null;
    }

    private PersonConvertingQuery() throws SQLException {
        personDeletor = PersonDeletingQuery.getInstance();
        personCreator = PersonCreatingQuery.getInstanceForConverting();
        examGetter = ExamNoteQueries.getInstanceForGettingQueries();
    }

    public static IPersonConvertingQuery getInstance() throws SQLException {
        if (personConvertingQuery == null) {
            personConvertingQuery = new PersonConvertingQuery();
        }
        return personConvertingQuery;
    }

    @Override
    public void convertToNormalStudent(String userName, String pass, String name, String lastName, int uid, int newUid, int balance, int debt, int lessonCredit, Map<Integer, Integer> lessonTeacherMap, String phoneNumber) throws SQLException {
        convertStudent(userName, pass, name, lastName, newUid, balance, debt, lessonCredit, lessonTeacherMap, super.getAccess().getNormalStudentTable(), super.getAccess().getNormalStudentCourseTable(), super.getAccess().getNormalStudentLoginInfosTable(), phoneNumber);
        convertToNormalStudentExamInfo(uid, newUid, lessonTeacherMap);
        personDeletor.deletePersonFromDb(uid);
    }

    @Override
    public void convertToWorkingStudent(String userName, String pass, String name, String lastName, int uid, int newUid, int balance, int debt, int lessonCredit, Map<Integer, Integer> lessonTeacherMap, String phoneNumber) throws SQLException {
        convertStudent(userName, pass, name, lastName, newUid, balance, debt, lessonCredit, lessonTeacherMap, super.getAccess().getWorkingStudentTable(), super.getAccess().getWorkingStudentCourseTable(), super.getAccess().getWorkingStudentLoginInfosTable(), phoneNumber);
        convertToWorkingStudentExamInfo(uid, newUid, lessonTeacherMap);
        personDeletor.deletePersonFromDb(uid);
    }

    private void convertStudent(String userName, String pass, String name, String lastName, int newUID, int balance, int debt, int lessonCredit, Map<Integer, Integer> lessonTeacherMap, String personTableToConvert, String courseTable, String loginTable, String phoneNumber) throws SQLException {
        String studentConvertingQuery = getConvertingStudentInfoStringQuery(name, lastName, newUID, balance, debt, lessonCredit, phoneNumber, personTableToConvert);
        super.runUpdatingQuery(studentConvertingQuery);
        convertLessonInfo(newUID, lessonTeacherMap, courseTable);
        convertLoginInfo(newUID, userName, pass, loginTable);
    }

    private String getConvertingStudentInfoStringQuery(String name, String lastName, int newUID, int balance, int debt, int lessonCredit, String phoneNumber, String personTableToConvert) {
        return "insert into " + personTableToConvert + " values ('" + name + "','" + lastName + "'," + newUID + "," + balance + "," + debt + "," + lessonCredit + ",'" + phoneNumber + "') ;";
    }

    private void convertLessonInfo(int uid, Map<Integer, Integer> lessonTeacherMap, String tableName) throws SQLException {
        for (Map.Entry<Integer, Integer> entry : lessonTeacherMap.entrySet()) {
            int lessonUID = entry.getKey();
            int teacherUID = entry.getValue();
            String convertingLessonInfoQuery = getLessonInfoConvertingQueryString(tableName, uid, lessonUID, teacherUID);
            System.out.println(convertingLessonInfoQuery);
            super.runUpdatingQuery(convertingLessonInfoQuery);
        }
    }

    private String getLessonInfoConvertingQueryString(String tableName, int uid, int lessonUID, int teacherUID) {
        return "insert into " + tableName + " values (" + uid + "," + lessonUID + "," + teacherUID + ") ;";
    }

    private void convertToNormalStudentExamInfo(int uid, int newUID, Map<Integer, Integer> lessonTeacherMap) throws SQLException {
        for (Map.Entry<Integer, Integer> lessonAndTeacherEntry : lessonTeacherMap.entrySet()) {
            int midterm = examGetter.getWorkingStudentMidtermValues(uid, lessonAndTeacherEntry.getKey());
            int finalValue = examGetter.getWorkingStudentFinalValues(uid, lessonAndTeacherEntry.getKey());
            int average = examGetter.getWorkingStudentAverage(uid, lessonAndTeacherEntry.getKey());
            convertStudentExamInfo(newUID, lessonAndTeacherEntry.getKey(), midterm, finalValue, average, super.getAccess().getNormalStudentExamTable());
        }
    }

    private void convertToWorkingStudentExamInfo(int uid, int newUID, Map<Integer, Integer> lessonTeacherMap) throws SQLException {
        for (Map.Entry<Integer, Integer> lessonAndTeacherEntry : lessonTeacherMap.entrySet()) {
            int midterm = examGetter.getNormalStudentMidtermValues(uid, lessonAndTeacherEntry.getKey());
            int finalValue = examGetter.getNormalStudentFinalValues(uid, lessonAndTeacherEntry.getKey());
            int average = examGetter.getNormalStudentAverage(uid, lessonAndTeacherEntry.getKey());
            convertStudentExamInfo(newUID, lessonAndTeacherEntry.getKey(), midterm, finalValue, average, super.getAccess().getWorkingStudentExamTable());
        }
    }

    private void convertStudentExamInfo(int newUID, int lessonUID, int midterm, int finalValue, int average, String tableToConvert) throws SQLException {
        String examConvertingQuery = getConvertingStudentExamQueryString(newUID, midterm, lessonUID, finalValue, average, tableToConvert);
        super.runUpdatingQuery(examConvertingQuery);
    }

    private String getConvertingStudentExamQueryString(int newUID, int midterm, int teacherUID, int finalValue, int average, String tableToConvert) {
        return "insert into " + tableToConvert + " values (" + newUID + "," + teacherUID + "," + midterm + "," + finalValue + "," + average + ") ;";
    }

    private void convertLoginInfo(int uid, String userName, String pass, String tableName) throws SQLException {
        String query = getLoginInfoConvertingQueryString(tableName, uid, userName, pass);
        super.runUpdatingQuery(query);
    }

    private String getLoginInfoConvertingQueryString(String tableName, int uid, String userName, String pass) {
        return "insert into " + tableName + " values (" + uid + ",'" + userName + "','" + pass + "');";
    }

}
