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
import com.kaan.schoolmanagementmaven.dataaccess.query.ITeacherInformationQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonFetchingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonInformationQuery;
import com.kaan.schoolmanagementmaven.exception.InvalidExamNoteException;
import com.kaan.schoolmanagementmaven.exception.InvalidStudentUIDException;
import com.kaan.schoolmanagementmaven.log.ILogManager;
import com.kaan.schoolmanagementmaven.log.LogManager;

/**
 *
 * @author kaan
 * 
 */
public class Teacher extends Person {

    private static ILogManager logManager;
    private static Optional<ILogManager> optinalLogManager;
    private ILessonCourseQuery lessonCourseQuery;
    private IPersonFetchingQueries personFetcher;
    private int salary;
    private Lesson branch;
    private IExamNoteSettingQueries examSetter;
    private IExamNoteGettingQueries examGetter;
    private ILessonFetchingQuery lessonFetcher;
    private ITeacherInformationQueries teacherInfo ;

    static {
        optinalLogManager = Optional.ofNullable(logManager);
    }

    public Teacher(String userName, String pass, String name, String lastName, int uid, int balance, Lesson branch, int salary, String phoneNumber) throws SQLException {
        super(userName, pass, name, lastName, uid, balance, phoneNumber);
        this.branch = branch;
        this.salary = salary;
        lessonCourseQuery = LessonCourseQuery.getInstance();
        personFetcher = PersonFetchingQueries.getInstance();
        examSetter = ExamNoteQueries.getInstanceForSettingQueries();
        examGetter = ExamNoteQueries.getInstanceForGettingQueries();
        lessonFetcher = LessonFetchingQuery.getInstance();
        teacherInfo = PersonInformationQuery.getInstanceForTeacher() ;
    }

    public List<String> showStudentList() throws SQLException {
        List<String> studentList = new ArrayList();
        int teacherUID = personFetcher.getPersonUIDByNameAndLastname(super.getName(), super.getLastName());
        List<ResultSet> normalStudentsOfTeacher = lessonCourseQuery.getAllNormalStudentInfo(teacherUID);
        List<ResultSet> workingStudentsOfTeacher = lessonCourseQuery.getAllWorkingStudentInfo(teacherUID);
        addStudentList(normalStudentsOfTeacher, studentList);
        addStudentList(workingStudentsOfTeacher, studentList);
        return studentList;
    }

    private void addStudentList(List<ResultSet> studentsOfTeacher, List<String> studentList) throws SQLException {
        for (ResultSet student : studentsOfTeacher) {
            while (student.next()) {
                String studentName = student.getString("name");
                String studentLastname = student.getString("last_name");
                int studentUID = student.getInt("UID");
                studentList.add(studentName + "\n" + studentLastname + "\n" + studentUID + "\n");
            }
        }
    }

    public void enterMidtermNote(int studentUID, int value) throws InvalidExamNoteException, InvalidStudentUIDException, SQLException {
        throwExceptionIfInvalidExamNote(value);
        int teacherUID = personFetcher.getPersonUIDByNameAndLastname(super.getName(), super.getLastName());
        throwExceptionIfNotTeachersStudent(studentUID, teacherUID);
        int lessonUID = teacherInfo.getBranchId(teacherUID);
        examSetter.setMidtermNote(studentUID, lessonUID, value);
    }

    public void enterFinalNote(int studentUID, int value) throws InvalidExamNoteException, InvalidStudentUIDException, SQLException {
        throwExceptionIfInvalidExamNote(value);
        int teacherUID = personFetcher.getPersonUIDByNameAndLastname(super.getName(), super.getLastName());
        throwExceptionIfNotTeachersStudent(studentUID, teacherUID);
        int lessonUID = teacherInfo.getBranchId(teacherUID);
        examSetter.setFinalNote(studentUID, lessonUID, value);
    }

    private void throwExceptionIfInvalidExamNote(int note) throws InvalidExamNoteException {
        if (note < 0) {
            throw new InvalidExamNoteException();
        }
    }

    private void throwExceptionIfNotTeachersStudent(int studentUID , int teacherUID) throws InvalidStudentUIDException , SQLException{
        List<Integer> normalStudentUIDs = lessonCourseQuery.getAllNormalStudentUID(teacherUID) ;
        List<Integer> workingStudentUIDs = lessonCourseQuery.getAllWorkingStudentUID(teacherUID);
        if (!(isInStudentUIDList(studentUID, normalStudentUIDs) || isInStudentUIDList(studentUID, workingStudentUIDs))) throw new InvalidStudentUIDException () ;
    }

    private boolean isInStudentUIDList(int uid, List<Integer> studentUIDs) {
        for (int studentUID : studentUIDs) {
            if (uid == studentUID) {
                return true;
            }
        }
        return false;
    }

    int setAverage(int studentUID, int lessonUID) throws SQLException {
        int midterm = examGetter.getNormalStudentMidtermValues(studentUID, lessonUID);
        if (midterm != -1) {
            int finalValue = examGetter.getNormalStudentFinalValues(studentUID, lessonUID);
            int midtermRate = lessonFetcher.getLessonAverageMidtermRate(lessonUID);
            int finalRate = lessonFetcher.getLessonAverageFinalRate(lessonUID);
            int result = (int) ((((float) midtermRate / 100)) * midterm + (((float) finalRate / 100)) * finalValue);
            examSetter.setAverage(studentUID, lessonUID, result);
            return result;
        }
        midterm = examGetter.getWorkingStudentMidtermValues(studentUID, lessonUID);
        int finalValue = examGetter.getWorkingStudentFinalValues(studentUID, lessonUID);
        int midtermRate = lessonFetcher.getLessonAverageMidtermRate(lessonUID);
        int finalRate = lessonFetcher.getLessonAverageFinalRate(lessonUID);
        int result = (int) ((((float) midtermRate / 100)) * midterm + (((float) finalRate / 100)) * finalValue);
        examSetter.setAverage(studentUID, lessonUID, result);
        return result;
    }

    public static ILogManager getLogManager() {
        return Teacher.logManager;
    }

    public static void setLogManager(File logFile) throws IOException {
        Teacher.logManager = new LogManager(logFile);
    }

    public static Optional<ILogManager> getOptinalLogManager() {
        return optinalLogManager;
    }

}
