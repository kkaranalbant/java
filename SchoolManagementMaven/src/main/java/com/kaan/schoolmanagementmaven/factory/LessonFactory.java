/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.factory;

import com.kaan.schoolmanagementmaven.dataaccess.query.LessonFetchingQuery;
import com.kaan.schoolmanagementmaven.lesson.Lesson;
import java.sql.SQLException;
import java.sql.ResultSet;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonCreatingQuery;
import com.kaan.schoolmanagementmaven.exception.NotUniqueLessonNameException;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonFetchingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonCreatingQuery;
import com.kaan.schoolmanagementmaven.exception.InvalidFinalRateException;
import com.kaan.schoolmanagementmaven.exception.InvalidLessonCreditException;
import com.kaan.schoolmanagementmaven.exception.InvalidMidtermRateException;
import com.kaan.schoolmanagementmaven.exception.InvalidQuotaException;

/**
 *
 * @author kaan
 * 
 */
public class LessonFactory implements ILessonFactory {

    private ILessonFetchingQuery lessonFetchingQuery;

    private ILessonCreatingQuery lessonCreator;

    private static ILessonFactory lessonFactory = null;

    private ILessonUIDGenerator generator;

    public LessonFactory() throws SQLException {
        lessonFetchingQuery = LessonFetchingQuery.getInstance();
        lessonCreator = LessonCreatingQuery.getInstance();
        generator = Generator.getLessonUIDGenerator();
    }

    public static ILessonFactory getInstance() throws SQLException {
        if (lessonFactory == null) {
            lessonFactory = new LessonFactory();
        }
        return lessonFactory;
    }

    @Override
    public void createNewLesson(String name, int lessonCredit, int lessonHour, int quota, int midtermRate, int finalRate) throws SQLException, NotUniqueLessonNameException, InvalidQuotaException, InvalidLessonCreditException, InvalidFinalRateException, InvalidMidtermRateException {
        throwExceptionIfInvalidFinalRate(finalRate);
        throwExceptionIfInvalidLessonCredit(lessonCredit);
        throwExceptionIfInvalidMidtermRate(midtermRate);
        throwExceptionIfInvalidQuota(quota);
        throwExceptionIfNotUniqueName(name);
        int uid = generator.generateUIDForLesson();
        lessonCreator.addLessonToDb(name, lessonCredit, lessonHour, uid, quota, midtermRate, finalRate);
    }

    private void throwExceptionIfInvalidLessonCredit(int lessonCredit) {
        if (lessonCredit <= 0) {
            throw new InvalidLessonCreditException();
        }
    }

    private void throwExceptionIfInvalidFinalRate(int finalRate) {
        if (finalRate < 0 || finalRate > 100) {
            throw new InvalidFinalRateException();
        }
    }

    private void throwExceptionIfInvalidMidtermRate(int midtermRate) {
        if (midtermRate < 0 || midtermRate > 100) {
            throw new InvalidMidtermRateException();
        }
    }

    private void throwExceptionIfInvalidQuota(int quota) {
        if (quota <= 0) {
            throw new InvalidQuotaException();
        }
    }

    private void throwExceptionIfNotUniqueName(String lessonName) throws NotUniqueLessonNameException, SQLException {
        for (String currentName : lessonFetchingQuery.getAllLessonNames()) {
            if (currentName.equals(lessonName)) {
                throw new NotUniqueLessonNameException();
            }
        }
    }

    @Override
    public Lesson createLessonWhichExistInDb(int lessonUID) throws SQLException {
        ResultSet lessonInDb = lessonFetchingQuery.getLessonInfo(lessonUID);
        String lessonName = null;
        int lessonCredit = 0;
        int lessonHour = 0;
        int quota = 0;
        int midtermRate = 0;
        int finalRate = 0;
        while (lessonInDb.next()) {
            lessonName = lessonInDb.getString("lesson_name");
            lessonCredit = lessonInDb.getInt("lesson_credit");
            lessonHour = lessonInDb.getInt("lesson_hour");
            quota = lessonInDb.getInt("quota");
            midtermRate = lessonInDb.getInt("average_midterm_rate");
            finalRate = lessonInDb.getInt("average_final_rate");
        }
        Lesson lesson = new Lesson(lessonName, lessonCredit, lessonHour, lessonUID, quota, midtermRate, finalRate);
        return lesson;
    }

}
