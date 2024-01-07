/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import com.kaan.schoolmanagementmaven.factory.ILessonFactory;
import com.kaan.schoolmanagementmaven.factory.LessonFactory;
import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.dataaccess.query.ILessonDeletingQuery;
import com.kaan.schoolmanagementmaven.dataaccess.query.LessonDeletingQuery;
import com.kaan.schoolmanagementmaven.exception.InvalidLessonCreditException;
import com.kaan.schoolmanagementmaven.exception.InvalidQuotaException;
import com.kaan.schoolmanagementmaven.exception.InvalidUIDException;
import com.kaan.schoolmanagementmaven.exception.NotUniqueLessonNameException;

/**
 *
 * @author kaan
 */
public class AdminLessonManager implements IAdminLessonManager {

    private ILessonFactory lessonFactory;
    private ILessonDeletingQuery lessonDeletor ;
    private static IAdminLessonManager lessonManager;

    private AdminLessonManager() throws SQLException {
        lessonFactory = LessonFactory.getInstance();
        lessonDeletor = LessonDeletingQuery.getInstance();
    }

    public static IAdminLessonManager getInstance() throws SQLException {
        if (lessonManager == null) {
            lessonManager = new AdminLessonManager();
        }
        return lessonManager;
    }

    @Override
    public void addLesson(String name, int lesson_credit, int lessonHour, int quota , int midtermRate , int finalRate) throws SQLException, NotUniqueLessonNameException, InvalidQuotaException, InvalidLessonCreditException {
        lessonFactory.createNewLesson(name, lesson_credit, lessonHour, quota , midtermRate , finalRate);
    }

    @Override
    public void removeLesson(int uid) throws InvalidUIDException , SQLException{
        int value = lessonDeletor.deleteLesson(uid);
        if (value == 0) throw new InvalidUIDException () ;
    }

}
