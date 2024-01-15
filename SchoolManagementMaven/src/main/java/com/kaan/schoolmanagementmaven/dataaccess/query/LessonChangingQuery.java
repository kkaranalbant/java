/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;

/**
 *
 * @author kaan
 * 
 */
public class LessonChangingQuery extends Query implements ILessonChangingQuery {

    private static ILessonChangingQuery lessonChanger;

    static {
        lessonChanger = null;
    }

    private LessonChangingQuery() throws SQLException {

    }

    public static ILessonChangingQuery getInstance() throws SQLException {
        if (lessonChanger == null) {
            lessonChanger = new LessonChangingQuery();
        }
        return lessonChanger;
    }

    @Override
    public void changeLessonName(String newName, int lessonUID) throws SQLException {
        String query = getLessonChangingQuery("lesson_name", newName, lessonUID) ;
        super.runUpdatingQuery(query);
    }

    @Override
    public void changeLessonCredit(int newCredit, int lessonUID) throws SQLException {
        String query = getLessonChangingQuery("lesson_credit", newCredit, lessonUID);
        super.runUpdatingQuery(query);
    }

    @Override
    public void changeLessonHour(int newLessonHour, int lessonUID) throws SQLException {
        String query = getLessonChangingQuery("lesson_hour", newLessonHour, lessonUID);
        super.runUpdatingQuery(query);
    }

    @Override
    public void changeLessonQuota(int newQuota, int lessonUID) throws SQLException {
        String query = getLessonChangingQuery("quota", newQuota, lessonUID);
        super.runUpdatingQuery(query);
    }

    @Override
    public void changeLessonMidtermRate(int newMidtermRate, int lessonUID) throws SQLException {
        String query = getLessonChangingQuery("average_midterm_rate", newMidtermRate, lessonUID);
        super.runUpdatingQuery(query);
    }

    @Override
    public void changeLessonFinalRate(int newFinalRate, int lessonUID) throws SQLException {
        String query = getLessonChangingQuery("average_final_rate", newFinalRate, lessonUID);
        super.runUpdatingQuery(query);
    }

    private String getLessonChangingQuery(String column, int value, int lessonUID) {
        return "update " + super.getAccess().getLessonTable() + " set " + column + " = " + value + " where lesson_UID = " + lessonUID + " ;";
    }
    
    private String getLessonChangingQuery(String column, String value, int lessonUID) {
        return "update " + super.getAccess().getLessonTable() + " set " + column + " = '" + value + "' where lesson_UID = " + lessonUID + " ;";
    }

}
