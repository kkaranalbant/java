/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kaan
 *
 */
public class LessonFetchingQuery extends Query implements ILessonFetchingQuery {
    
    private static ILessonFetchingQuery lessonCreatingQuery = null;
    
    private LessonFetchingQuery() throws SQLException {
    }
    
    public static ILessonFetchingQuery getInstance() throws SQLException {
        if (lessonCreatingQuery == null) {
            lessonCreatingQuery = new LessonFetchingQuery();
        }
        return lessonCreatingQuery;
    }
    
    @Override
    public ResultSet getNormalStudentLessonUID(int normalStudenUID) throws SQLException {
        String query = getNormalStudentLessonListQueryString(normalStudenUID);
        return super.runGettingQuery(query);
    }
    
    @Override
    public ResultSet getWorkingStudentLessonUID(int workingStudentUID) throws SQLException {
        String query = getWorkingStudentLessonListQueryString(workingStudentUID);
        return super.runGettingQuery(query);
    }
    
    @Override
    public ResultSet getLessonInfo(int uid) throws SQLException {
        String query = getLessonInfoQueryString(uid);
        return super.runGettingQuery(query);
    }
    
    private String getLessonAttributeString(String query, String column) throws SQLException {
        ResultSet attributes = super.runGettingQuery(query);
        attributes.next();
        return attributes.getString(column);
    }
    
    private int getLessonAttributeInteger(String query, String column) throws SQLException {
        ResultSet attributes = super.runGettingQuery(query);
        attributes.next();
        return attributes.getInt(column);
    }
    
    @Override
    public String getLessonNamebyUID(int uid) throws SQLException {
        String query = getLessonNameByUIDQueryString(uid);
        return getLessonAttributeString(query, "lesson_name");
    }
    
    @Override
    public int getLessonUIDByLessonName(String lessonName) throws SQLException {
        String query = getLessonUIDByLessonNameQueryString(lessonName);
        return getLessonAttributeInteger(query, "lesson_UID");
    }
    
    @Override
    public int getLessonQuota(String lessonName) throws SQLException {
        int lessonUID = getLessonUIDByLessonName(lessonName);
        String query = getLessonQuotaQueryString(lessonUID);
        return getLessonAttributeInteger(query, "quota");
    }
    
    @Override
    public List<String> getAllLessonNames() throws SQLException {
        String query = getAllLessonNamesQueryString();
        ResultSet lessonNamesInDb = super.runGettingQuery(query);
        List<String> lessonNames = new ArrayList();
        addLessonNamestoList(lessonNamesInDb, lessonNames);
        return lessonNames;
    }
    
    private void addLessonNamestoList(ResultSet lessonNamesInDb, List<String> lessonNames) throws SQLException {
        while (lessonNamesInDb.next()) {
            String lessonName = lessonNamesInDb.getString("lesson_name");
            lessonNames.add(lessonName);
        }
    }
    
    @Override
    public int getLessonAverageMidtermRate(int uid) throws SQLException {
        String query = getLessonAverageMidtermRateQueryString(uid);
        return getLessonAttributeInteger(query, "average_midterm_rate");
    }
    
    @Override
    public int getLessonAverageFinalRate(int uid) throws SQLException {
        String query = getLessonAverageFinalRateQueryString(uid);
        return getLessonAttributeInteger(query, "average_final_rate");
    }
    
    @Override
    public List<String> getNormalStudentLessonNames(int uid) throws SQLException {
        ResultSet lessonUIDsOfNormalStudent = getNormalStudentLessonUID(uid);
        List<String> lessonNamesOfNormalStudent = new ArrayList();
        addLessonNamesOfStudentToList(lessonUIDsOfNormalStudent, lessonNamesOfNormalStudent);
        return lessonNamesOfNormalStudent;
    }
    
    @Override
    public List<String> getWorkingStudentLessonNames(int uid) throws SQLException {
        ResultSet lessonUIDsOfWorkingStudent = getWorkingStudentLessonUID(uid);
        List<String> lessonNamesOfWorkingStudent = new ArrayList();
        addLessonNamesOfStudentToList(lessonUIDsOfWorkingStudent, lessonNamesOfWorkingStudent);
        return lessonNamesOfWorkingStudent;
    }
    
    private void addLessonNamesOfStudentToList(ResultSet lessonUIDsOfStudent, List<String> lessonNamesOfStudent) throws SQLException {
        while (lessonUIDsOfStudent.next()) {
            int lessonUID = lessonUIDsOfStudent.getInt("lesson_UID");
            String lessonName = getLessonNamebyUID(lessonUID);
            lessonNamesOfStudent.add(lessonName);
        }
    }
    
    @Override
    public ResultSet getAllLessons() throws SQLException {
        String query = getAllLessonsQueryString();
        return super.runGettingQuery(query);
    }
    
    private String getAllLessonsQueryString() {
        return "select * from " + super.getAccess().getLessonTable() + " ;";
    }
    
    private String getLessonNameByUIDQueryString(int uid) throws SQLException {
        return "select lesson_name from " + super.getAccess().getLessonTable() + " where lesson_UID = " + uid;
    }
    
    private String getLessonUIDByLessonNameQueryString(String lessonName) throws SQLException {
        System.out.println("Lesson uid command : " + "select lesson_UID from " + super.getAccess().getLessonTable() + " where lesson_name = '" + lessonName + "' ;");
        return "select lesson_UID from " + super.getAccess().getLessonTable() + " where lesson_name = '" + lessonName + "' ;";
    }
    
    private String getNormalStudentLessonListQueryString(int uid) {
        return "select lesson_UID from " + super.getAccess().getNormalStudentCourseTable() + " where normal_student_UID = " + uid;
    }
    
    private String getWorkingStudentLessonListQueryString(int uid) {
        return "select lesson_UID from " + super.getAccess().getWorkingStudentCourseTable() + " where working_student_UID = " + uid;
    }
    
    private String getLessonInfoQueryString(int uid) {
        return "select * from " + super.getAccess().getLessonTable() + " where lesson_UID = " + uid;
    }
    
    private String getAllLessonNamesQueryString() {
        return "select lesson_name from " + super.getAccess().getLessonTable() + " ;";
    }
    
    private String getLessonQuotaQueryString(int lessonUID) {
        return "select quota from " + super.getAccess().getLessonTable() + " where lesson_UID = " + lessonUID + " ;";
    }
    
    private String getLessonAverageMidtermRateQueryString(int lessonUID) {
        return "select average_midterm_rate from " + super.getAccess().getLessonTable() + " where lesson_UID = " + lessonUID + " ;";
    }
    
    private String getLessonAverageFinalRateQueryString(int lessonUID) {
        return "select average_final_rate from " + super.getAccess().getLessonTable() + " where lesson_UID = " + lessonUID + " ;";
    }
    
}
