/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kaan
 */
public class LessonCourseQuery extends Query implements ILessonCourseQuery {

    private static ILessonCourseQuery courseQuery;
    private static ILessonFetchingQuery lessonInfoFetcher;

    static {
        courseQuery = null;
    }

    private LessonCourseQuery() throws SQLException {
        lessonInfoFetcher = LessonFetchingQuery.getInstance();
    }

    public static ILessonCourseQuery getInstance() throws SQLException {
        if (courseQuery == null) {
            courseQuery = new LessonCourseQuery();
        }
        return courseQuery;
    }

    @Override
    public void addLessonAndStudentToNormalStudentCourse(String lessonName, int studentUID, int teacherUID) throws SQLException {
        addLessonAndStudentToCourse(lessonName, studentUID, teacherUID, super.getAccess().getNormalStudentCourseTable());
    }

    @Override
    public void removeLessonAndStudentFromNormalStudentCourse(String lessonName, int studentUID) throws SQLException {
        removeLessonAndStudentFromCourse(lessonName, studentUID, super.getAccess().getNormalStudentCourseTable(), "normal_student_UID");
    }

    @Override
    public void addLessonAndStudentToWorkingStudentCourse(String lessonName, int studentUID, int teacherUID) throws SQLException {
        addLessonAndStudentToCourse(lessonName, studentUID, teacherUID, super.getAccess().getWorkingStudentCourseTable());
    }

    @Override
    public void removeLessonAndStudentFromWorkingStudentCourse(String lessonName, int studentUID) throws SQLException {
        removeLessonAndStudentFromCourse(lessonName, studentUID, super.getAccess().getWorkingStudentCourseTable(), "working_student_UID");
    }

    @Override
    public List<ResultSet> getAllNormalStudentInfo(int teacherUID) throws SQLException{
        return getStudentInfo(teacherUID, "normal_student_UID", super.getAccess().getNormalStudentCourseTable(),super.getAccess().getNormalStudentTable());
    }

    @Override
    public List<ResultSet> getAllWorkingStudentInfo(int teacherUID) throws SQLException {
        return getStudentInfo(teacherUID, "working_student_UID", super.getAccess().getWorkingStudentCourseTable(),super.getAccess().getWorkingStudentTable());

    }

    @Override
    public List<Integer> getAllNormalStudentUID(int teacherUID) throws SQLException {
        return getAllStudentUID(teacherUID, super.getAccess().getNormalStudentCourseTable(), "normal_student_UID") ;
    }

    @Override
    public List<Integer> getAllWorkingStudentUID(int teacherUID) throws SQLException {
        return getAllStudentUID(teacherUID, super.getAccess().getWorkingStudentCourseTable(), "working_student_UID") ;
    }

    @Override
    public int findTeacherUIDFromNormalStudentCourse(int studentUID, int lessonUID) throws SQLException {
        return findTeacherUID(studentUID, lessonUID, super.getAccess().getNormalStudentCourseTable() , "normal_student_UID");
    }

    @Override
    public int findTeacherYUDFromWorkingStudentCourse(int studentUID, int lessonUID) throws SQLException {
        return findTeacherUID(studentUID, lessonUID, super.getAccess().getWorkingStudentCourseTable() , "working_student_UID");
    }
    
    private int findTeacherUID (int studentUID , int lessonUID , String tableName , String columnName) throws SQLException {
        String query = "select teacher_UID from "+tableName+" where "+ columnName+ " = "+studentUID+" and lesson_UID = "+lessonUID+" ;" ;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery() ;
        resultSet.next() ;
        return resultSet.getInt("teacher_UID") ;
    }
    
    private List <Integer> getAllStudentUID (int teacherUID , String tableName , String columnName) throws SQLException {
        String query = "select "+columnName+" from "+tableName+" where teacher_UID = "+teacherUID+" ; "; 
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery() ;
        List <Integer> result = new ArrayList () ;
        while (resultSet.next()) {
            result.add(resultSet.getInt(columnName)) ;
        }
        return result ;
    }

    private List<ResultSet> getStudentInfo(int teacherUID, String columnName, String tableName , String tableName1) throws SQLException {
        String query = "select " + columnName + " from "+tableName+" where teacher_UID = "+teacherUID ; 
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery() ;
        List <ResultSet> resultSetList = new ArrayList () ;
        while (resultSet.next()) {
            query = "select name , last_name , UID from "+ tableName1+" where UID = "+resultSet.getInt(columnName) ;
            super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
            resultSetList.add(super.getPreparedStatement().executeQuery());
        }
        return resultSetList ;
    }
    private void addLessonAndStudentToCourse(String lessonName, int studentUID, int teacherUID, String tableName) throws SQLException {
        int lessonUID = lessonInfoFetcher.getLessonUIDByLessonName(lessonName);
        String query = "insert into " + tableName + " values (" + studentUID + "," + lessonUID + "," + teacherUID + ") ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    private void removeLessonAndStudentFromCourse(String lessonName, int studentUID, String tableName, String columnName) throws SQLException {
        int lessonUID = lessonInfoFetcher.getLessonUIDByLessonName(lessonName);
        String query = "delete from " + tableName + " where lesson_UID = " + lessonUID + " and " + columnName + " = " + studentUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

}
