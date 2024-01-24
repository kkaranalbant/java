/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.dataaccess.query.DefaultValuesQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.IDefaultValuesQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonDeletingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonFetchingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonDeletingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonFetchingQuery;
import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.exception.InvalidLessonCreditException;
import com.kaan.schoolmanagementmaven.exception.InvalidQuotaException;
import com.kaan.schoolmanagementmaven.exception.InvalidUIDException;
import com.kaan.schoolmanagementmaven.exception.NotUniqueLessonNameException;
import com.kaan.schoolmanagementmaven.exception.ReachedMaximumRowNumberException;
import com.kaan.schoolmanagementmaven.factory.ILessonFactory;
import com.kaan.schoolmanagementmaven.factory.LessonFactory;
import java.sql.ResultSet;

/**
 *
 * @author kaan
 *
 */
public class AdminLessonManager implements IAdminLessonManager {

    private static IAdminLessonManager adminLessonManager;
    private ILessonFactory lessonFactory;
    private ILessonDeletingQuery lessonDeletor;
    private ILessonFetchingQuery lessonFetcher;
    private IDefaultValuesQuery defValQuery;

    private AdminLessonManager() throws SQLException {
        lessonFactory = LessonFactory.getInstance();
        lessonDeletor = LessonDeletingQuery.getInstance();
        lessonFetcher = LessonFetchingQuery.getInstance();
        defValQuery = DefaultValuesQuery.getInstance();

    }

    public static IAdminLessonManager getInstance() throws SQLException {
        if (adminLessonManager == null) {
            adminLessonManager = new AdminLessonManager();
        }
        return adminLessonManager;
    }

    @Override
    public void addLesson(String name, int lesson_credit, int lessonHour, int quota, int midtermRate, int finalRate) throws SQLException, NotUniqueLessonNameException, InvalidQuotaException, InvalidLessonCreditException, ReachedMaximumRowNumberException {
        throwExceptionIfReachedMaxRowNumberInLessonTable();
        lessonFactory.createNewLesson(name, lesson_credit, lessonHour, quota, midtermRate, finalRate);
    }

    @Override
    public void removeLesson(int uid) throws InvalidUIDException, SQLException {
        boolean isDeleted = lessonDeletor.deleteLesson(uid);
        if (!isDeleted) {
            throw new InvalidUIDException();
        }
    }

    private void throwExceptionIfReachedMaxRowNumberInLessonTable() throws SQLException, ReachedMaximumRowNumberException {
        boolean isReached = isReachedMaxRowNumberInLessonTable();
        if (isReached) {
            throw new ReachedMaximumRowNumberException();
        }
    }

    private boolean isReachedMaxRowNumberInLessonTable() throws SQLException {
        int rowNumber = getRowNumber(lessonFetcher.getAllLessons());
        System.out.println("satir sayisi : "+rowNumber);
        int lessonUIDRange = defValQuery.getDefaultLessonUIDBound() - defValQuery.getDefaultLessonUIDOrigin() + 1;
        System.out.println("UID range araligi : "+lessonUIDRange);
        return rowNumber == lessonUIDRange;
    }

    private int getRowNumber(ResultSet lessons) throws SQLException {
        int counter = 0;
        while (lessons.next()) {
            System.out.println("satir sayisi dongusune girildi");
            counter++;
        }
        return counter;
    }

}
