/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.person;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.kaan.schoolmanagementmaven.lesson.Lesson;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonCourseQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonFetchingQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonCourseQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonFetchingQueries;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import com.kaan.schoolmanagementmaven.dataaccess.query.ExamNoteQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.IExamNoteGettingQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.IExamNoteSettingQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonFetchingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonFetchingQuery;
import com.kaan.schoolmanagementmaven.exception.InvalidExamNoteException;
import com.kaan.schoolmanagementmaven.exception.InvalidStudentUIDException;
import com.kaan.schoolmanagementmaven.log.ILogManager;
import com.kaan.schoolmanagementmaven.log.LogManager;

/**
 *
 * @author kaan
 */
public class Teacher extends Person {

    private static ILogManager logManager ;
    private static Optional <ILogManager> optinalLogManager ;
    private ILessonCourseQuery lessonCourseQuery;
    private IPersonFetchingQueries personFetcher;
    private int salary;
    private Lesson branch;
    private IExamNoteSettingQueries examSetter;
    private IExamNoteGettingQueries examGetter;
    private ILessonFetchingQuery lessonFetcher;
    
    static {
        optinalLogManager = Optional.ofNullable(logManager);
    }

    public Teacher(String userName, String pass, String name, String lastName, int uid, int balance, Lesson branch, int salary , String phoneNumber) throws SQLException {
        super(userName, pass, name, lastName, uid, balance , phoneNumber);
        this.branch = branch;
        this.salary = salary;
        lessonCourseQuery = LessonCourseQuery.getInstance();
        personFetcher = PersonFetchingQueries.getInstance();
        examSetter = ExamNoteQueries.getInstanceForSettingQueries();
        examGetter = ExamNoteQueries.getInstanceForGettingQueries();
        lessonFetcher = LessonFetchingQuery.getInstance();
    }

    public List<String> showStudentList() throws SQLException {
        List<String> result = new ArrayList();
        int teacherUID = personFetcher.getPersonUIDByNameAndLastname(super.getName(), super.getLastName());
        List<ResultSet> resultSetListForNormalStudent = lessonCourseQuery.getAllNormalStudentInfo(teacherUID);
        List<ResultSet> resultSetListForWorkingStudent = lessonCourseQuery.getAllWorkingStudentInfo(teacherUID);
        for (ResultSet resultSet : resultSetListForNormalStudent) {
            while (resultSet.next()) {
                result.add(resultSet.getString("name") + "\n" + resultSet.getString("last_name") + "\n" + resultSet.getInt(("UID")));
            }
        }
        for (ResultSet resultSet : resultSetListForWorkingStudent) {
            while (resultSet.next()) {
                result.add(resultSet.getString("name") + "\n" + resultSet.getString("last_name") + "\n" + resultSet.getInt(("UID")));
            }
        }
        return result;
    }

    public void enterMidtermNote(int studentUID, int value) throws InvalidExamNoteException, InvalidStudentUIDException, SQLException {
        if (value < 0) {
            throw new InvalidExamNoteException();
        }
        int teacherUID = personFetcher.getPersonUIDByNameAndLastname(super.getName(), super.getLastName());
        List<Integer> normalStudentList = lessonCourseQuery.getAllNormalStudentUID(teacherUID);
        List<Integer> workingStudentList = lessonCourseQuery.getAllWorkingStudentUID(teacherUID);
        int lessonUID = lessonFetcher.getBranchId(teacherUID);
        for (int currentStudentUID : normalStudentList) {
            if (currentStudentUID == studentUID) {
                examSetter.setMidtermNote(studentUID, lessonUID, value);
                setAverage(studentUID, lessonUID);
                return;
            }
        }
        for (int currentStudentUID : workingStudentList) {
            if (currentStudentUID == studentUID) {
                examSetter.setMidtermNote(studentUID, lessonUID, value);
                setAverage(studentUID, lessonUID);
                return;
            }
        }
        throw new InvalidStudentUIDException();
    }

    public void enterFinalNote(int studentUID, int value) throws InvalidExamNoteException, InvalidStudentUIDException, SQLException {
        if (value < 0) {
            throw new InvalidExamNoteException();
        }
        int teacherUID = personFetcher.getPersonUIDByNameAndLastname(super.getName(), super.getLastName());
        int lessonUID = lessonFetcher.getBranchId(teacherUID);
        List<Integer> normalStudentList = lessonCourseQuery.getAllNormalStudentUID(teacherUID);
        List<Integer> workingStudentList = lessonCourseQuery.getAllWorkingStudentUID(teacherUID);
        for (int currentStudentUID : normalStudentList) {
            if (currentStudentUID == studentUID) {
                examSetter.setFinalNote(studentUID, lessonUID, value);
                return;
            }
        }
        for (int currentStudentUID : workingStudentList) {
            if (currentStudentUID == studentUID) {
                examSetter.setFinalNote(studentUID, lessonUID, value);
                return;
            }
        }
        throw new InvalidStudentUIDException();
    }

    int setAverage(int studentUID, int lessonUID) throws SQLException {
        int midterm = examGetter.getNormalStudentMidtermValues(studentUID, lessonUID);
        if (midterm != -1) {
            int finalValue = examGetter.getNormalStudentFinalValues(studentUID, lessonUID);
            int midtermRate = lessonFetcher.getLessonAverageMidtermRate(lessonUID);
            int finalRate = lessonFetcher.getLessonAverageFinalRate(lessonUID);
            int result = (int) ((((float)midtermRate / 100)) * midterm + (((float)finalRate / 100)) * finalValue);
            examSetter.setAverage(studentUID, lessonUID, result);
            return result ;
        }
        midterm = examGetter.getWorkingStudentMidtermValues(studentUID, lessonUID);
        int finalValue = examGetter.getWorkingStudentFinalValues(studentUID, lessonUID);
        int midtermRate = lessonFetcher.getLessonAverageMidtermRate(lessonUID);
        int finalRate = lessonFetcher.getLessonAverageFinalRate(lessonUID);
        int result = (int) ((((float)midtermRate / 100)) * midterm + (((float)finalRate / 100)) * finalValue);
        examSetter.setAverage(studentUID, lessonUID, result);
        return result ;
    }
    
    public static ILogManager getLogManager () {
        return Teacher.logManager ;
    }
    
    public static void setLogManager (File logFile) throws IOException{
        Teacher.logManager = new LogManager (logFile) ;
    }

    public static Optional<ILogManager> getOptinalLogManager() {
        return optinalLogManager;
    }
    
    
}
