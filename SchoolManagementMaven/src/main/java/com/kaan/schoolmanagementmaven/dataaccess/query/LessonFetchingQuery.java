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
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeQuery();
    }

    @Override
    public ResultSet getWorkingStudentLessonUID(int workingStudentUID) throws SQLException {
        String query = getWorkingStudentLessonListQueryString(workingStudentUID);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeQuery();
    }

    @Override
    public ResultSet getLessonInfo(int uid) throws SQLException {
        String query = getLessonInfoQueryString(uid);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        return super.getPreparedStatement().executeQuery();
    }

    @Override
    public int getLessonUIDByLessonName(String lessonName) throws SQLException {
        String query = getLessonUIDByLessonNameQueryString(lessonName);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        int result = 0 ;
        while (resultSet.next()) {
            result = resultSet.getInt("lesson_UID");
        }
        return result;
    }

    @Override
    public List<String> getAllLessonNames() throws SQLException {
        String query = getAllLessonNamesQueryString();
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        List<String> lessonNames = new ArrayList();
        while (resultSet.next()) {
            String lessonName = resultSet.getString("lesson_name");
            lessonNames.add(lessonName);
        }
        return lessonNames;
    }

    @Override
    public int getBranchIdWithTeacherUID(int teacherUID) throws SQLException {
        String query = getBranchUIDWithTeacherUIDQueryString(teacherUID);
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        resultSet.next();
        int result = resultSet.getInt("lesson_UID");
        return result;
    }

    @Override
    public int getBranchId(int teacherUID) throws SQLException {
        String query = "select lesson_UID from " + super.getAccess().getTeacherBranchTable() + " where teacher_UID = " + teacherUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        resultSet.next();
        return resultSet.getInt("lesson_UID");
    }

    @Override
    public List<String> getNormalStudentLessonNames(int uid) throws SQLException {
        ResultSet resultSet = getNormalStudentLessonUID(uid);
        List<String> result = new ArrayList();
        while (resultSet.next()) {
            int lessonUID = resultSet.getInt("lesson_UID");
            ResultSet lessonInfo = getLessonInfo(lessonUID) ;
            lessonInfo.next();
            result.add(lessonInfo.getString("lesson_name"));
        }
        return result;
    }

    @Override
    public List<String> getWorkingStudentLessonNames(int uid) throws SQLException {
        ResultSet resultSet = getWorkingStudentLessonUID(uid);
        List<String> result = new ArrayList();
        while (resultSet.next()) {
            int lessonUID = resultSet.getInt("lesson_UID");
            ResultSet lessonInfo = getLessonInfo(lessonUID) ;
            lessonInfo.next();
            result.add(lessonInfo.getString("lesson_name"));
        }
        return result;
    }

    @Override
    public int getLessonQuota(String lessonName) throws SQLException {
        int lessonUID = getLessonUIDByLessonName(lessonName);
        String query = "select quota from " + super.getAccess().getLessonTable() + " where lesson_UID = " + lessonUID + " ;";
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        resultSet.next();
        return resultSet.getInt("quota");
    }

    @Override
    public int getLessonAverageMidtermRate(int uid) throws SQLException {
        String query = "select average_midterm_rate from "+super.getAccess().getLessonTable()+" where lesson_UID = "+uid+" ;" ;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));;
        ResultSet resultSet = super.getPreparedStatement().executeQuery() ;
        int result = 0 ;
        while (resultSet.next()) {
            result = resultSet.getInt("average_midterm_rate");
        }
        return result ;
    }

    @Override
    public int getLessonAverageFinalRate(int uid) throws SQLException {
        String query = "select average_final_rate from "+super.getAccess().getLessonTable()+" where lesson_UID = "+uid+" ;" ;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));;
        ResultSet resultSet = super.getPreparedStatement().executeQuery() ;
        int result = 0 ;
        while (resultSet.next()) {
            result = resultSet.getInt("average_final_rate");
        }
        return result ;
    }

    @Override
    public String getLessonNamebyUID(int uid) throws SQLException {
        String query = "select lesson_name from "+super.getAccess().getLessonTable()+" where lesson_UID = "+uid ;
        super.setPreparedStatement(super.getAccess().getConnection().prepareStatement(query));
        ResultSet resultSet = super.getPreparedStatement().executeQuery();
        resultSet.next() ;
        return resultSet.getString("lesson_name");
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

    private String getLessonUIDByLessonNameQueryString(String lessonName) {
        return "select lesson_UID from " + super.getAccess().getLessonTable() + " where lesson_name = '" + lessonName + "' ;";
    }

    private String getBranchUIDWithTeacherUIDQueryString(int uid) {
        return "select lesson_UID from " + super.getAccess().getTeacherBranchTable() + " where teacher_UID = " + uid + " ;";
    }

}
