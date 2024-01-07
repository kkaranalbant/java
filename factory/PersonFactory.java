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

/**
 *
 * @author kaan
 */
public class PersonFactory implements IPersonFactory {

    private IPersonFetchingQueries personFetchingQuery;
    private ILessonFetchingQuery lessonCreatingQuery;
    private static IPersonFactory personFactory = null;
    private ILessonFactory lessonFactory;

    private PersonFactory() throws SQLException {
        personFetchingQuery = PersonFetchingQueries.getInstance();
        lessonCreatingQuery = LessonFetchingQuery.getInstance();
        lessonFactory = LessonFactory.getInstance();
    }

    public static IPersonFactory getInstance() throws SQLException {
        if (personFactory == null) {
            personFactory = new PersonFactory();
        }
        return personFactory;
    }

    @Override
    public Student createNormalStudentWhichExistsInDb(String userName, String pass, int uid) throws SQLException {
        ResultSet resultSet = personFetchingQuery.getNormalStudentInfo(uid);
        String name = null;
        String lastName = null;
        String phoneNumber = null;
        int lessonCredit = 0;
        int balance = 0;
        int debt = 0;
        while (resultSet.next()) {
            name = resultSet.getString("name");
            lastName = resultSet.getString("last_name");
            phoneNumber = resultSet.getString("phone_number");
            lessonCredit = resultSet.getInt("student_lesson_credit");
            balance = resultSet.getInt("balance");
            debt = resultSet.getInt("debt");

        }
        List<Lesson> lessonList = getNormalStudentLessonList(uid);
        return new Student(userName, pass, debt, lessonCredit, name, lastName, uid, balance, lessonList, phoneNumber);
    }

    @Override
    public WorkingStudent createWorkingStudentWhichExistsInDb(String userName, String pass, int uid) throws SQLException {
        ResultSet resultSet = personFetchingQuery.getWorkingStudentInfo(uid);
        String name = null;
        String lastName = null;
        String phoneNumber = null;
        int lessonCredit = 0;
        int balance = 0;
        int debt = 0;
        while (resultSet.next()) {
            name = resultSet.getString("name");
            lastName = resultSet.getString("last_name");
            phoneNumber = resultSet.getString("phone_number");
            lessonCredit = resultSet.getInt("student_lesson_credit");
            balance = resultSet.getInt("balance");
            debt = resultSet.getInt("debt");
        }
        List<Lesson> lessonList = getWorkingStudentLessonList(uid);
        return new WorkingStudent(userName, pass, debt, lessonCredit, name, lastName, uid, balance, lessonList, phoneNumber);
    }

    @Override
    public Teacher createTeacherWhichExistsInDb(String userName, String pass, int uid) throws SQLException {
        ResultSet resultSet = personFetchingQuery.getTeacherInfo(uid);
        String name = null;
        String lastName = null;
        String phoneNumber = null ;
        int balance = 0;
        int salary = 0;
        int lessonUID = lessonCreatingQuery.getBranchIdWithTeacherUID(uid);
        Lesson branch = lessonFactory.createLessonWhichExistInDb(lessonUID);
        while (resultSet.next()) {
            name = resultSet.getString("name");
            lastName = resultSet.getString("last_name");
            phoneNumber = resultSet.getString("phone_number");
            balance = resultSet.getInt("balance");
            salary = resultSet.getInt("salary");
        }
        return new Teacher(userName, pass, name, lastName, uid, balance, branch, salary , phoneNumber);
    }

    private List<Lesson> getNormalStudentLessonList(int uid) throws SQLException {
        ResultSet resultSet = lessonCreatingQuery.getNormalStudentLessonUID(uid);
        List<Lesson> result = new ArrayList();
        while (resultSet.next()) {
            int lessonUID = resultSet.getInt("lesson_UID");
            Lesson lesson = lessonFactory.createLessonWhichExistInDb(lessonUID);
            result.add(lesson);
        }
        return result;
    }

    private List<Lesson> getWorkingStudentLessonList(int uid) throws SQLException {
        ResultSet resultSet = lessonCreatingQuery.getWorkingStudentLessonUID(uid);
        List<Lesson> result = new ArrayList();
        while (resultSet.next()) {
            int lessonUID = resultSet.getInt("lesson_UID");
            Lesson lesson = lessonFactory.createLessonWhichExistInDb(lessonUID);
            result.add(lesson);
        }
        return result;
    }

}
