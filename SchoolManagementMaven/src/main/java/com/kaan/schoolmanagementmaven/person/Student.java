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
import com.kaan.schoolmanagementmaven.exception.InvalidLessonCreditException;
import com.kaan.schoolmanagementmaven.exception.InvalidMaxDebtCreditException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import javax.swing.JOptionPane;
import com.kaan.schoolmanagementmaven.dataaccess.query.ExamNoteQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.IExamNoteGettingQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.IExamNoteSettingQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonFetchingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonFetchingQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonFetchingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonFetchingQueries;
import com.kaan.schoolmanagementmaven.exception.NotSufficentCreditException;
import com.kaan.schoolmanagementmaven.exception.OutOfQuotaException;
import com.kaan.schoolmanagementmaven.lesson.ILessonManager;
import com.kaan.schoolmanagementmaven.lesson.Lesson;
import com.kaan.schoolmanagementmaven.lesson.LessonManager;
import com.kaan.schoolmanagementmaven.log.ILogManager;
import com.kaan.schoolmanagementmaven.log.LogManager;

/**
 *
 * @author kaan
 */
public class Student extends Person {

    private static int defaultLessonCreditNumber;
    private static int defaultMaxDebtCredit;
    private static ILogManager logManager;
    private static Optional <ILogManager> optionalLogManager ;
    private final List<Lesson> lessonList;
    private int debt;
    private int lessonCredit;
    private IStudentConvertingManager studentConvertingManager;
    private IPersonChangingManager personChangingManager;
    private IPersonFetchingQueries personFetcher;
    private ILessonManager lessonManager;
    private ILessonFetchingQuery lessonFetcher;
    private IExamNoteGettingQueries examGetter;
    private IExamNoteSettingQueries examSetter;

    static {
        IDefaultValuesQuery query = null;
        try {
            query = DefaultValuesQuery.getInstance();
            defaultLessonCreditNumber = query.getDefaultLessonCredit();
            defaultMaxDebtCredit = query.getDefaultMaxDebtCredit();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        optionalLogManager = Optional.ofNullable(logManager);
    }

    public Student(String userName, String pass, int debt, int lessonCredit, String name, String lastName, int uid, int balance, List<Lesson> lessonList , String phoneNumber) throws SQLException {
        super(userName, pass, name, lastName, uid, balance , phoneNumber);
        this.debt = debt;
        this.lessonCredit = lessonCredit;
        this.lessonList = lessonList;
        studentConvertingManager = PersonManager.getInstanceForConvertingManager();
        personChangingManager = PersonManager.getInstanceForChangingManager();
        personFetcher = PersonFetchingQueries.getInstance();
        lessonManager = LessonManager.getInstance();
        examGetter = ExamNoteQueries.getInstanceForGettingQueries();
        examSetter = ExamNoteQueries.getInstanceForSettingQueries();
        lessonFetcher = LessonFetchingQuery.getInstance();
    }

    public static void setDefaultLessonCredit(int value) throws InvalidLessonCreditException {
        boolean isValidValue = StudentValidation.isValidDefaultLessonCredit(value);
        if (isValidValue) {
            defaultLessonCreditNumber = value;
        } else {
            throw new InvalidLessonCreditException();
        }
    }

    public static void setDefaultMaxDebtCredit(int value) throws InvalidMaxDebtCreditException {
        boolean isValidValue = StudentValidation.isValidMaxDebtCredit(value);
        if (isValidValue) {
            defaultMaxDebtCredit = value;
        } else {
            throw new InvalidMaxDebtCreditException();
        }
    }

    public void addLesson(Lesson lesson, int teacherUID) throws NotSufficentCreditException, SQLException, OutOfQuotaException {
        if (lesson.getLessonCredit() > lessonCredit) {
            throw new NotSufficentCreditException();
        } else {
            lessonList.add(lesson);
            lessonCredit -= lesson.getLessonCredit();
            personChangingManager.changeNormalStudentLessonCredit(personFetcher.getPersonUIDByNameAndLastname(super.getName(), super.getLastName()), lessonCredit);
            lessonManager.addLessonToNormalStudentCourse(lesson.getName(), personFetcher.getPersonUIDByNameAndLastname(super.getName(), super.getLastName()), teacherUID);
        }
    }

    public String showExamInfo() throws SQLException {
        StringBuilder sb = new StringBuilder();
        int uid = personFetcher.getPersonUIDByNameAndLastname(super.getName(), super.getLastName()) ;
        List <String> lessonNames = lessonFetcher.getNormalStudentLessonNames(uid);
        List <Integer> lessonUIDList = new ArrayList() ;
        for (String currentLessonName : lessonNames) {
            lessonUIDList.add(lessonFetcher.getLessonUIDByLessonName(currentLessonName));
        }
        for (int currentLessonUID : lessonUIDList) {
            String lessonName = lessonFetcher.getLessonNamebyUID(currentLessonUID) ;
            int currentExamMidterm = examGetter.getNormalStudentMidtermValues(uid, currentLessonUID) ;
            int currentExamFinal = examGetter.getNormalStudentFinalValues(uid, currentLessonUID);
            int currentExamAverage = examGetter.getNormalStudentAverage(uid, currentLessonUID);
            sb.append("Lesson name :    ").append(lessonName).append("    ").append("Midterm :   ").append(currentExamMidterm).append("    ").append("final :    ").append(currentExamFinal).append("     ").append("Average :    ").append(currentExamAverage).append("\n");
        }
        return sb.toString() ;
    }

    public void removeLesson(Lesson lesson) throws SQLException {
        lessonList.remove(lesson);
        lessonCredit += lesson.getLessonCredit();
        personChangingManager.changeNormalStudentLessonCredit(personFetcher.getPersonUIDByNameAndLastname(super.getName(), super.getLastName()), lessonCredit);
        lessonManager.removeLessonFromNormalStudentCourse(lesson.getName(), personFetcher.getPersonUIDByNameAndLastname(super.getName(), super.getLastName()));
    }

    public void beWorkingStudent() throws SQLException {
        studentConvertingManager.convertToWorkingStudent(this);
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public int getDebt() {
        return debt;
    }

    public int getLessonCredit() {
        return lessonCredit;
    }

    void setLessonCredit(int lessonCredit) {
        this.lessonCredit = lessonCredit;
    }

    IStudentConvertingManager getStudentConvertingManager() {
        return studentConvertingManager;
    }

    IPersonChangingManager getPersonChangingManager() {
        return personChangingManager;
    }

    IPersonFetchingQueries getPersonFetcher() {
        return personFetcher;
    }

    ILessonManager getLessonManager() {
        return lessonManager;
    }
    
    ILessonFetchingQuery getLessonFetcher() {
        return lessonFetcher ;
    }

    public IExamNoteGettingQueries getExamGetter() {
        return examGetter;
    }

    public IExamNoteSettingQueries getExamSetter() {
        return examSetter;
    }
    
    public static ILogManager getLogManager () {
        return Student.logManager ;
    }
    
    public static Optional <ILogManager> getOptionalLogManager() {
        return optionalLogManager ;
    }
    
    public static void setLogManager (File logFile) throws IOException{
        Student.logManager = new LogManager (logFile) ;
    }
    
}
