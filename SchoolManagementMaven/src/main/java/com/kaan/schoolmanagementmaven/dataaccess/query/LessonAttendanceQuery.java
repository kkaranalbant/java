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
public class LessonAttendanceQuery extends Query implements ILessonAttendanceQuery {

    private static ILessonAttendanceQuery attendanceQuery;

    private ILessonFetchingQuery lessonFetcher;

    static {
        attendanceQuery = null;
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
        int lessonUID = lessonFetcher.getLessonUIDByLessonName(lessonName);
        String query = "update " + super.getAccess().getAttendanceTable() + " set attendance = attendance + 1 where lesson_UID = " + lessonUID + " ; ";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public void decreaseAttendance(String lessonName) throws SQLException {
        int lessonUID = lessonFetcher.getLessonUIDByLessonName(lessonName);
        String query = "update " + super.getAccess().getAttendanceTable() + " set attendance = attendance - 1 where lesson_UID = " + lessonUID + " ; ";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public int getLessonAttendance(String lessonName) throws SQLException {
        int lessonUID = lessonFetcher.getLessonUIDByLessonName(lessonName);
        String query = "select attendance from " + super.getAccess().getAttendanceTable() + " where lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        resultSet.next();
        return resultSet.getInt("attendance");
    }

    @Override
    public void addLessonAttendanceToDb(String lessonName) throws SQLException {
        int lessonUID = lessonFetcher.getLessonUIDByLessonName(lessonName);
        String query = "insert into " + super.getAccess().getAttendanceTable() + " values (" + lessonUID + "," + 0 + ") ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public void removeLessonAttendanceFromDb(String lessonName) throws SQLException {
        int lessonUID = lessonFetcher.getLessonUIDByLessonName(lessonName);
        String query = "delete from " + super.getAccess().getAttendanceTable() + " where lesson_UID = " + lessonUID;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

}
