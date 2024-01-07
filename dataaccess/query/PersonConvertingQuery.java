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
 */
public class PersonConvertingQuery extends Query implements IPersonConvertingQuery {

    private static IPersonConvertingQuery personConvertingQuery;
    private IPersonDeletingQuery personDeletor;
    private IExamNoteGettingQueries examGetter ;

    static {
        personConvertingQuery = null;
    }

    private PersonConvertingQuery() throws SQLException {
        personDeletor = PersonDeletingQuery.getInstance();
        examGetter = ExamNoteQueries.getInstanceForGettingQueries() ;
    }

    public static IPersonConvertingQuery getInstance() throws SQLException {
        if (personConvertingQuery == null) {
            personConvertingQuery = new PersonConvertingQuery();
        }
        return personConvertingQuery;
    }

    @Override
    public void convertToNormalStudent(String userName, String pass, String name, String lastName, int uid, int newUid, int balance, int debt, int lessonCredit, Map<Integer, Integer> lessonTeacherMap , String phoneNumber) throws SQLException {
        convertStudent(userName, pass, name, lastName, newUid, balance, debt, lessonCredit, lessonTeacherMap, super.getAccess().getNormalStudentTable(), super.getAccess().getNormalStudentCourseTable(), super.getAccess().getNormalStudentLoginInfosTable() , phoneNumber);
        convertToNormalStudentExamInfo(uid, newUid, lessonTeacherMap);
        personDeletor.deletePersonFromDb(uid);
    }

    @Override
    public void convertToWorkingStudent(String userName, String pass, String name, String lastName, int uid, int newUid, int balance, int debt, int lessonCredit , Map<Integer, Integer> lessonTeacherMap , String phoneNumber) throws SQLException {
        convertStudent(userName, pass, name, lastName, newUid, balance, debt, lessonCredit, lessonTeacherMap, super.getAccess().getWorkingStudentTable(), super.getAccess().getWorkingStudentCourseTable(), super.getAccess().getWorkingStudentLoginInfosTable() , phoneNumber);
        convertToWorkingStudentExamInfo(uid, newUid, lessonTeacherMap);
        personDeletor.deletePersonFromDb(uid);
    }

    private void convertStudent(String userName, String pass, String name, String lastName, int uid, int balance, int debt, int lessonCredit, Map<Integer, Integer> lessonTeacherMap, String personTable, String courseTable, String loginTable , String phoneNumber) throws SQLException {
        String query = "insert into " + personTable + " values ('" + name + "','" + lastName + "'," + uid + "," + balance + "," + debt + "," + lessonCredit +","+ phoneNumber +") ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
        convertLessonInfo(uid, lessonTeacherMap, courseTable);
        convertLoginInfo(uid, userName, pass, loginTable);
    }

    private void convertLessonInfo(int uid, Map<Integer, Integer> lessonTeacherMap, String tableName) throws SQLException {
        for (Map.Entry<Integer, Integer> entry : lessonTeacherMap.entrySet()) {
            int lessonUID = entry.getKey();
            int teacherUID = entry.getValue();
            String query = "insert into " + tableName + " values (" + uid + "," + lessonUID + "," + teacherUID + ") ;";
            super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
            super.getPreparedStatement().executeUpdate();
        }
    }

    private void convertToNormalStudentExamInfo(int uid, int newUID , Map <Integer , Integer> lessonTeacherMap) throws SQLException{
        for (Map.Entry<Integer , Integer> entry : lessonTeacherMap.entrySet()) {
            int midterm = examGetter.getWorkingStudentMidtermValues(uid, entry.getKey()) ;
            int finalValue = examGetter.getWorkingStudentFinalValues(uid, entry.getKey()) ;
            int average = examGetter.getWorkingStudentAverage(uid, entry.getKey());
            String query = "insert into "+super.getAccess().getNormalStudentExamTable()+" values ("+newUID+","+entry.getKey()+","+midterm+","+finalValue+","+average+") ;" ;
            super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
            super.getPreparedStatement().executeUpdate() ;
        }
    }
    
    private void convertToWorkingStudentExamInfo(int uid, int newUID , Map <Integer , Integer> lessonTeacherMap) throws SQLException{
        for (Map.Entry<Integer , Integer> entry : lessonTeacherMap.entrySet()) {
            int midterm = examGetter.getNormalStudentMidtermValues(uid, entry.getKey()) ;
            int finalValue = examGetter.getNormalStudentFinalValues(uid, entry.getKey()) ;
            int average = examGetter.getNormalStudentAverage(uid, entry.getKey());
            String query = "insert into "+super.getAccess().getWorkingStudentExamTable()+" values ("+newUID+","+entry.getKey()+","+midterm+","+finalValue+","+average+") ;" ;
            System.out.println(query);
            super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
            super.getPreparedStatement().executeUpdate() ;
        }
    }

    private void convertLoginInfo(int uid, String userName, String pass, String tableName) throws SQLException {
        String query = "insert into " + tableName + " values (" + uid + ",'" + userName + "','" + pass + "');";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

}
