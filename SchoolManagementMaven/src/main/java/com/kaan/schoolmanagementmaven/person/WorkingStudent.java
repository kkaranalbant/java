/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.person;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.kaan.schoolmanagementmaven.dataaccess.query.DefaultValuesQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IDefaultValuesQuery;
import com.kaan.schoolmanagementmaven.lesson.Lesson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import com.kaan.schoolmanagementmaven.exception.NotSufficentCreditException;
import com.kaan.schoolmanagementmaven.exception.OutOfQuotaException;
import com.kaan.schoolmanagementmaven.log.ILogManager;
import com.kaan.schoolmanagementmaven.log.LogManager;

/**
 *
 * @author kaan
 */
public class WorkingStudent extends Student {

    private static int costForPerHour;
    private static ILogManager logManager;
    private static Optional<ILogManager> optionalLogManager;

    static {
        try {
            IDefaultValuesQuery defValQuery = DefaultValuesQuery.getInstance();
            costForPerHour = defValQuery.getDefaultCostForPerHourForWorkingStudent();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        optionalLogManager = Optional.ofNullable(logManager);
    }

    public WorkingStudent(String userName, String pass, int debt, int lessonCredit, String name, String lastName, int uid, int balance, List<Lesson> lessonList, String phoneNumber) throws SQLException {
        super(userName, pass, debt, lessonCredit, name, lastName, uid, balance, lessonList, phoneNumber);
    }

    public void beNormalStudent() throws SQLException {
        super.getStudentConvertingManager().convertToNormalStudent(this);
    }

    @Override
    public void addLesson(Lesson lesson, int teacherUID) throws NotSufficentCreditException, SQLException, OutOfQuotaException {
        throwExceptionIfNotSufficenCredit(teacherUID);
        super.getLessonList().add(lesson);
        super.setLessonCredit(super.getLessonCredit() - lesson.getLessonCredit());
        super.getPersonChangingManager().changeNormalStudentLessonCredit(super.getPersonFetcher().getPersonUIDByNameAndLastname(super.getName(), super.getLastName()), super.getLessonCredit());
        super.getLessonManager().addLessonToNormalStudentCourse(lesson.getName(), super.getPersonFetcher().getPersonUIDByNameAndLastname(super.getName(), super.getLastName()), teacherUID);
    }

    private void throwExceptionIfNotSufficenCredit(int lessonCredit) throws NotSufficentCreditException {
        if (lessonCredit > super.getLessonCredit()) {
            throw new NotSufficentCreditException();
        }
    }

    @Override
    public void removeLesson(Lesson lesson) throws SQLException {
        super.getLessonList().remove(lesson);
        super.setLessonCredit(super.getLessonCredit() + lesson.getLessonCredit());
        super.getPersonChangingManager().changeNormalStudentLessonCredit(super.getPersonFetcher().getPersonUIDByNameAndLastname(super.getName(), super.getLastName()), super.getLessonCredit());
        super.getLessonManager().removeLessonFromNormalStudentCourse(lesson.getName(), super.getPersonFetcher().getPersonUIDByNameAndLastname(super.getName(), super.getLastName()));
    }

    @Override
    public String showExamInfo() throws SQLException {
        int uid = super.getPersonFetcher().getPersonUIDByNameAndLastname(super.getName(), super.getLastName());
        List<String> lessonNames = super.getLessonFetcher().getWorkingStudentLessonNames(uid);
        List<Integer> lessonUIDs = new ArrayList();
        fillLessonUIDListFromLessonNameList(lessonNames, lessonUIDs);
        return createExamInfoString(uid, lessonUIDs);
    }

    public static ILogManager getLogManager() {
        return WorkingStudent.logManager;
    }

    public static void setLogManager(File logFile) throws IOException {
        WorkingStudent.logManager = new LogManager(logFile);
    }

    public static Optional<ILogManager> getOptionalLogManager() {
        return optionalLogManager;
    }

}
