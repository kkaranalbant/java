/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;

/**
 *
 * @author kaan
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
        String query = "update " + super.getAccess().getLessonTable() + " set lesson_name = '" + newName + "' where lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public void changeLessonCredit(int newCredit, int lessonUID) throws SQLException {
        String query = "update " + super.getAccess().getLessonTable() + " set lesson_credit = " + newCredit + " where lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public void changeLessonHour(int newLessonHour, int lessonUID) throws SQLException {
        String query = "update " + super.getAccess().getLessonTable() + " set lesson_hour = " + newLessonHour + " where lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public void changeLessonQuota(int newQutoa, int lessonUID) throws SQLException {
        String query = "update " + super.getAccess().getLessonTable() + " set quota = " + newQutoa + " where lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public void changeLessonMidtermRate(int newMidtermRate, int lessonUID) throws SQLException {
        String query = "update " + super.getAccess().getLessonTable() + " set average_midterm_rate = " + newMidtermRate + " where lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }

    @Override
    public void changeLessonFinalRate(int newFinalRate, int lessonUID) throws SQLException {
        String query = "update " + super.getAccess().getLessonTable() + " set average_final_rate = " + newFinalRate + " where lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        super.getPreparedStatement().executeUpdate();
    }
    
    

}
