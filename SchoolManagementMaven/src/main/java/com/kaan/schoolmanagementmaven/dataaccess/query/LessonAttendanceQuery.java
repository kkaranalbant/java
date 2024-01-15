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
public class LessonAttendanceQuery extends Query implements ILessonAttendanceQuery {

    private static ILessonAttendanceQuery attendanceQuery;

    private ILessonFetchingQuery lessonFetcher;
    
    private static final String INCREASING_VALUE ;
    private static final String DECREASING_VALUE ;
    

    static {
        attendanceQuery = null;
        INCREASING_VALUE = "+1" ;
        DECREASING_VALUE = "-1" ;
    }

    private LessonAttendanceQuery() throws SQLException {
        lessonFetcher = LessonFetchingQuery.getInstance();
    }

    public static ILessonAttendanceQuery getInstance() throws SQLException {
        if (attendanceQuery == null) {
            attendanceQuery = new LessonAttendanceQuery();
        }
        return attendanceQuery;
    }

    @Override
    public void increaseAttendance(String lessonName) throws SQLException {
        setAttendance(lessonName, INCREASING_VALUE);
    }

    @Override
    public void decreaseAttendance(String lessonName) throws SQLException {
        setAttendance(lessonName, DECREASING_VALUE);
    }

    private void setAttendance(String lessonName, String value) throws SQLException {
        int lessonUID = lessonFetcher.getLessonUIDByLessonName(lessonName);
        String query = getSettingAttendanceQueryString(lessonUID, value) ;
        super.runUpdatingQuery(query);
    }

    private String getSettingAttendanceQueryString (int lessonUID , String value) {
        return "update "+super.getAccess().getAttendanceTable()+" set attendance = attendance "+value+" where lesson_UID = "+lessonUID+" ; " ;
    }

    @Override
    public int getLessonAttendanceAmount(String lessonName) throws SQLException {
        ResultSet lessonAttendanceResultSet = getLessonAttendanceResultSet(lessonName);
        lessonAttendanceResultSet.next();
        return lessonAttendanceResultSet.getInt("attendance") ;
    }
    
    private ResultSet getLessonAttendanceResultSet (String lessonName) throws SQLException {
        int lessonUID = lessonFetcher.getLessonUIDByLessonName(lessonName);
        String query = getAttendanceAmountOfLessonQueryString(lessonUID);
        return super.runGettingQuery(query);
    }
    
    private String getAttendanceAmountOfLessonQueryString (int lessonUID) {
        return "select attendance from " + super.getAccess().getAttendanceTable() + " where lesson_UID = " + lessonUID + " ;";
    }

    @Override
    public void addLessonAttendanceToDb(String lessonName) throws SQLException {
        int lessonUID = lessonFetcher.getLessonUIDByLessonName(lessonName);
        String query = getLessonAttendanceAddingQueryString(lessonUID);
        super.runUpdatingQuery(query);
    }
    
    private String getLessonAttendanceAddingQueryString (int lessonUID) {
        return "insert into " + super.getAccess().getAttendanceTable() + " values (" + lessonUID + "," + 0 + ") ;";
    } 

    @Override
    public void removeLessonAttendanceFromDb(String lessonName) throws SQLException {
        int lessonUID = lessonFetcher.getLessonUIDByLessonName(lessonName);
        String query = getLessonAttendanceRemovingQueryString(lessonUID);
        super.runUpdatingQuery(query);
    }
    
    private String getLessonAttendanceRemovingQueryString (int lessonUID) throws SQLException {
        return "delete from " + super.getAccess().getAttendanceTable() + " where lesson_UID = " + lessonUID;
    }

}
