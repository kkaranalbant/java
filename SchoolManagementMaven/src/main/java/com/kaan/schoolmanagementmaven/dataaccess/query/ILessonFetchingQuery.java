/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author kaan
 *
 */
public interface ILessonFetchingQuery {

    public List<String> getNormalStudentLessonNames(int uid) throws SQLException;

    public List<String> getWorkingStudentLessonNames(int uid) throws SQLException;

    public ResultSet getNormalStudentLessonUID(int uid) throws SQLException;

    public ResultSet getWorkingStudentLessonUID(int uid) throws SQLException;

    public ResultSet getLessonInfo(int uid) throws SQLException;

    public List<String> getAllLessonNames() throws SQLException;

    public int getLessonUIDByLessonName(String lessonName) throws SQLException;

    public String getLessonNamebyUID(int uid) throws SQLException;

    public int getLessonQuota(String lessonName) throws SQLException;

    public int getLessonAverageMidtermRate(int uid) throws SQLException;

    public int getLessonAverageFinalRate(int uid) throws SQLException;

    public ResultSet getAllLessons() throws SQLException;
}
