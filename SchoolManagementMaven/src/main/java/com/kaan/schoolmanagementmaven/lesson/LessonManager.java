/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.lesson;

import com.kaan.schoolmanagementmaven.dataaccess.query.DefaultValuesQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.ExamNoteQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.IDefaultValuesQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IExamNoteAddingQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.IExamNoteRemovingQueries;
import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonAttendanceQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonChangingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonCourseQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonFetchingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.ITeacherInformationQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonAttendanceQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonChangingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonCourseQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonFetchingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonInformationQuery;
import com.kaan.schoolmanagementmaven.exception.OutOfQuotaException;
import com.kaan.schoolmanagementmaven.exception.ReachedMaximumRowNumberException;
import java.sql.ResultSet;

/**
 *
 * @author kaan
 *
 */
public class LessonManager implements ILessonManager {

    private ILessonCourseQuery lessonCourseQuery;
    private ILessonChangingQuery lessonChanger;
    private ILessonAttendanceQuery attendanceQuery;
    private ILessonFetchingQuery lessonFetcher;
    private ITeacherInformationQueries teacherInfo;
    private IExamNoteRemovingQueries examNoteRemovingObject;
    private IExamNoteAddingQueries examNoteAddingObject;
    private IDefaultValuesQuery defValQuery;
    private static ILessonManager lessonManager;

    static {
        lessonManager = null;
    }

    private LessonManager() throws SQLException {
        lessonCourseQuery = LessonCourseQuery.getInstance();
        lessonChanger = LessonChangingQuery.getInstance();
        attendanceQuery = LessonAttendanceQuery.getInstance();
        lessonFetcher = LessonFetchingQuery.getInstance();
        teacherInfo = PersonInformationQuery.getInstanceForTeacher();
        examNoteRemovingObject = ExamNoteQueries.getInstanceForRemovingQueries();
        examNoteAddingObject = ExamNoteQueries.getInstanceForAddingQueries();
        defValQuery = DefaultValuesQuery.getInstance();
    }

    public static ILessonManager getInstance() throws SQLException {
        if (lessonManager == null) {
            lessonManager = new LessonManager();
        }
        return lessonManager;
    }

    @Override
    public void addLessonToNormalStudentCourse(String lessonName, int studentUID, int teacherUID) throws SQLException, OutOfQuotaException {
        throwExceptionIfOutOfQuota(lessonName);
        lessonCourseQuery.addLessonAndStudentToNormalStudentCourse(lessonName, studentUID, teacherUID);
        int lessonUID = lessonFetcher.getLessonUIDByLessonName(lessonName);
        examNoteAddingObject.addNormalStudentToExamNoteTable(studentUID, lessonUID);
        attendanceQuery.increaseAttendance(lessonName);
    }

    @Override
    public void addLessonToWorkingStudentCourse(String lessonName, int studentUID, int teacherUID) throws SQLException, OutOfQuotaException {
        throwExceptionIfOutOfQuota(lessonName);
        lessonCourseQuery.addLessonAndStudentToWorkingStudentCourse(lessonName, studentUID, teacherUID);
        int lessonUID = lessonFetcher.getLessonUIDByLessonName(lessonName);
        examNoteAddingObject.addWorkingStudentToExamNoteTable(studentUID, lessonUID);
        attendanceQuery.increaseAttendance(lessonName);
    }

    private void throwExceptionIfOutOfQuota(String lessonName) throws OutOfQuotaException, SQLException {
        int quota = lessonFetcher.getLessonQuota(lessonName);
        int attendance = attendanceQuery.getLessonAttendanceAmount(lessonName);
        if (quota == attendance) {
            throw new OutOfQuotaException();
        }
    }

    @Override
    public void removeLessonFromNormalStudentCourse(String lessonName, int studentUID) throws SQLException {
        lessonCourseQuery.removeLessonAndStudentFromNormalStudentCourse(lessonName, studentUID);
        int lessonUID = lessonFetcher.getLessonUIDByLessonName(lessonName);
        examNoteRemovingObject.removeNormalStudentFromExamTable(studentUID, lessonUID);
        attendanceQuery.decreaseAttendance(lessonName);
    }

    @Override
    public void removeLessonFromWorkingStudentCourse(String lessonName, int studentUID) throws SQLException {
        lessonCourseQuery.removeLessonAndStudentFromWorkingStudentCourse(lessonName, studentUID);
        int lessonUID = lessonFetcher.getLessonUIDByLessonName(lessonName);
        examNoteRemovingObject.removeWorkingStudentFromExamTable(studentUID, lessonUID);
        attendanceQuery.decreaseAttendance(lessonName);
    }

    

}
