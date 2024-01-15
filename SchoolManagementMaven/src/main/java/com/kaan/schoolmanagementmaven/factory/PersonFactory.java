/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.factory;

import com.kaan.schoolmanagementmaven.dataaccess.query.LessonFetchingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonFetchingQueries;
import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.person.Student;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.kaan.schoolmanagementmaven.lesson.Lesson;
import com.kaan.schoolmanagementmaven.person.Teacher;
import com.kaan.schoolmanagementmaven.person.WorkingStudent;
import com.kaan.schoolmanagementmaven.dataaccess.query.IPersonFetchingQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonFetchingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.ITeacherInformationQueries;
import com.kaan.schoolmanagementmaven.dataaccess.query.PersonInformationQuery;

/**
 *
 * @author kaan
 * 
 */
public class PersonFactory implements IPersonFactory {

    private IPersonFetchingQueries personFetchingQuery;
    private ILessonFetchingQuery lessonCreatingQuery;
    private ITeacherInformationQueries teacherInfo;
    private static IPersonFactory personFactory = null;
    private ILessonFactory lessonFactory;

    private PersonFactory() throws SQLException {
        personFetchingQuery = PersonFetchingQueries.getInstance();
        lessonCreatingQuery = LessonFetchingQuery.getInstance();
        lessonFactory = LessonFactory.getInstance();
        teacherInfo = PersonInformationQuery.getInstanceForTeacher();
    }

    public static IPersonFactory getInstance() throws SQLException {
        if (personFactory == null) {
            personFactory = new PersonFactory();
        }
        return personFactory;
    }

    @Override
    public Student createNormalStudentWhichExistsInDb(String userName, String pass, int uid) throws SQLException {
        ResultSet normalStudent = personFetchingQuery.getNormalStudentInfo(uid);
        String name = null;
        String lastName = null;
        String phoneNumber = null;
        int lessonCredit = 0;
        int balance = 0;
        int debt = 0;
        while (normalStudent.next()) {
            name = normalStudent.getString("name");
            lastName = normalStudent.getString("last_name");
            phoneNumber = normalStudent.getString("phone_number");
            lessonCredit = normalStudent.getInt("student_lesson_credit");
            balance = normalStudent.getInt("balance");
            debt = normalStudent.getInt("debt");
        }
        List<Lesson> lessonList = getNormalStudentLessonList(uid);
        return new Student(userName, pass, debt, lessonCredit, name, lastName, uid, balance, lessonList, phoneNumber);
    }

    @Override
    public WorkingStudent createWorkingStudentWhichExistsInDb(String userName, String pass, int uid) throws SQLException {
        ResultSet workingStudent = personFetchingQuery.getWorkingStudentInfo(uid);
        String name = null;
        String lastName = null;
        String phoneNumber = null;
        int lessonCredit = 0;
        int balance = 0;
        int debt = 0;
        while (workingStudent.next()) {
            name = workingStudent.getString("name");
            lastName = workingStudent.getString("last_name");
            phoneNumber = workingStudent.getString("phone_number");
            lessonCredit = workingStudent.getInt("student_lesson_credit");
            balance = workingStudent.getInt("balance");
            debt = workingStudent.getInt("debt");
        }
        List<Lesson> lessonList = getWorkingStudentLessonList(uid);
        return new WorkingStudent(userName, pass, debt, lessonCredit, name, lastName, uid, balance, lessonList, phoneNumber);
    }

    @Override
    public Teacher createTeacherWhichExistsInDb(String userName, String pass, int uid) throws SQLException {
        ResultSet teacher = personFetchingQuery.getTeacherInfo(uid);
        String name = null;
        String lastName = null;
        String phoneNumber = null;
        int balance = 0;
        int salary = 0;
        int lessonUID = teacherInfo.getBranchId(uid);
        Lesson branch = lessonFactory.createLessonWhichExistInDb(lessonUID);
        while (teacher.next()) {
            name = teacher.getString("name");
            lastName = teacher.getString("last_name");
            phoneNumber = teacher.getString("phone_number");
            balance = teacher.getInt("balance");
            salary = teacher.getInt("salary");
        }
        return new Teacher(userName, pass, name, lastName, uid, balance, branch, salary, phoneNumber);
    }

    private List<Lesson> getNormalStudentLessonList(int uid) throws SQLException {
        ResultSet normalStudentLessonsInDb = lessonCreatingQuery.getNormalStudentLessonUID(uid);
        return getStudentLessonList(normalStudentLessonsInDb);
    }

    private List<Lesson> getWorkingStudentLessonList(int uid) throws SQLException {
        ResultSet workingStudentLessonsInDb = lessonCreatingQuery.getWorkingStudentLessonUID(uid);
        return getStudentLessonList(workingStudentLessonsInDb);
    }

    private List<Lesson> getStudentLessonList(ResultSet studentLessonsInDb) throws SQLException {
        List<Lesson> lessons = new ArrayList();
        while (studentLessonsInDb.next()) {
            int lessonUID = studentLessonsInDb.getInt("lesson_UID");
            lessons.add(lessonFactory.createLessonWhichExistInDb(lessonUID));
        }
        return lessons;
    }

}
