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
 * 
 */
public class LessonCourseQuery extends Query implements ILessonCourseQuery {

    private static ILessonCourseQuery courseQuery;
    private ILessonFetchingQuery lessonInfoFetcher;
    private IPersonFetchingQueries personFetcher ;
    private static final int INVALID_UID ; 

    static {
        courseQuery = null;
        INVALID_UID = -1 ;
    }

    private LessonCourseQuery() throws SQLException {
        lessonInfoFetcher = LessonFetchingQuery.getInstance();
        personFetcher = PersonFetchingQueries.getInstance();
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
    public int findTeacherUIDFromWorkingStudentCourse(int studentUID, int lessonUID) throws SQLException {
        return findTeacherUID(studentUID, lessonUID, super.getAccess().getWorkingStudentCourseTable() , "working_student_UID");
    }
    
    private int findTeacherUID (int studentUID , int lessonUID , String tableName , String columnName) throws SQLException {
        ResultSet teacherResultSet = findTeacher(studentUID, lessonUID, tableName, columnName);
        if (super.isEmptyResultSet(teacherResultSet)){
            return INVALID_UID;
        }
        return teacherResultSet.getInt("teacher_UID") ;
    }
    
    private ResultSet findTeacher (int studentUID , int lessonUID , String tableName , String columnName) throws SQLException {
        String query = getFindingTeacherQueryString(studentUID, lessonUID, tableName, columnName);
        System.out.println(query);
        return super.runGettingQuery(query);
    }
    
    private String getFindingTeacherQueryString (int studentUID , int lessonUID , String tableName , String columnName) throws SQLException {
        return "select teacher_UID from "+tableName+" where "+ columnName+ " = "+studentUID+" and lesson_UID = "+lessonUID+" ;" ;
    }
    
    private List <Integer> getAllStudentUID (int teacherUID , String tableName , String columnName) throws SQLException {
        String query = getAllStudentInfoFromCourseTableQueryString(teacherUID, tableName, columnName);
        ResultSet studentsResultSet = super.runGettingQuery(query);
        List <Integer> result = new ArrayList () ;
        addAllStudentUIDToList(studentsResultSet, result, columnName);
        return result ;
    }
    
    private void addAllStudentUIDToList (ResultSet studentsResultSet , List <Integer> uidList , String columnName) throws SQLException{
        while (studentsResultSet.next()) {
            uidList.add(studentsResultSet.getInt(columnName)) ;
        }
    }
    
    
    private List<ResultSet> getStudentInfo(int teacherUID, String columnName, String coursetableName , String tableName1) throws SQLException {
        String query = getAllStudentInfoFromCourseTableQueryString(teacherUID, coursetableName, columnName);
        ResultSet studentsResultSet = super.runGettingQuery(query);
        List <ResultSet> resultSetList = new ArrayList () ;
        addStudentResultSetToList(studentsResultSet, resultSetList, columnName);
        return resultSetList ;
    }
    
    private String getAllStudentInfoFromCourseTableQueryString (int teacherUID , String courseTableName , String columnName) {
        return "select " + columnName + " from "+courseTableName+" where teacher_UID = "+teacherUID ; 
    }
    
    private void addStudentResultSetToList (ResultSet studentsResultSet, List <ResultSet> studentList , String columnName) throws SQLException{
        while (studentsResultSet.next()) {
            int uid = studentsResultSet.getInt(columnName);
            ResultSet studentResultSet = personFetcher.getNormalStudentInfo(uid) ;
            ResultSet studentResultSetCopy = personFetcher.getNormalStudentInfo(uid);
            if (isStudentInNormalStudentTable(studentResultSet)) studentList.add(studentResultSetCopy) ;
            else {
                studentResultSet = personFetcher.getWorkingStudentInfo(uid);
                studentList.add(studentResultSet);
            }
        }
    }
    
    private boolean isStudentInNormalStudentTable (ResultSet studentResultSet) throws SQLException {
        if (studentResultSet.next()) return true ;
        return false ;
    }
    
    private void addLessonAndStudentToCourse(String lessonName, int studentUID, int teacherUID, String tableName) throws SQLException {
        int lessonUID = lessonInfoFetcher.getLessonUIDByLessonName(lessonName);
        String query = getAddingLessonAndStudentToCourseQueryString(studentUID, teacherUID, tableName , lessonUID) ;
        super.runUpdatingQuery(query);
    }
    
    private String getAddingLessonAndStudentToCourseQueryString (int studentUID, int teacherUID, String tableName , int lessonUID) throws SQLException {
        System.out.println("insert into " + tableName + " values (" + studentUID + "," + lessonUID + "," + teacherUID + ") ;");
        return "insert into " + tableName + " values (" + studentUID + "," + lessonUID + "," + teacherUID + ") ;";
    }

    private void removeLessonAndStudentFromCourse(String lessonName, int studentUID, String tableName, String columnName) throws SQLException {
        int lessonUID = lessonInfoFetcher.getLessonUIDByLessonName(lessonName);
        String query = getRemovingLessonAndStudentFromCourseQueryString(tableName, lessonUID, columnName, studentUID);
        super.runUpdatingQuery(query);
    }
    
    private String getRemovingLessonAndStudentFromCourseQueryString (String tableName , int lessonUID , String columnName , int studentUID) {
        return "delete from " + tableName + " where lesson_UID = " + lessonUID + " and " + columnName + " = " + studentUID + " ;";
    }

}
