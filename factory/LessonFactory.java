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
 */
public class LessonFactory implements ILessonFactory {

    private ILessonFetchingQuery lessonFetchingQuery;

    private ILessonCreatingQuery lessonCreator;

    private static ILessonFactory lessonFactory = null;
    
    private ILessonUIDGenerator generator ;

    public LessonFactory() throws SQLException {
        lessonFetchingQuery = LessonFetchingQuery.getInstance();
        lessonCreator = LessonCreatingQuery.getInstance();
        generator = Generator.getLessonUIDGenerator() ;
    }

    public static ILessonFactory getInstance() throws SQLException {
        if (lessonFactory == null) {
            lessonFactory = new LessonFactory();
        }
        return lessonFactory;
    }

    @Override
    public void createNewLesson(String name, int lesson_credit, int lessonHour, int quota , int midtermRate , int finalRate) throws SQLException, NotUniqueLessonNameException , InvalidQuotaException , InvalidLessonCreditException , InvalidFinalRateException ,  InvalidMidtermRateException{
        if (lesson_credit <= 0) throw new InvalidLessonCreditException () ; 
        if (midtermRate < 0 || midtermRate > 100) throw new InvalidMidtermRateException () ;
        if (finalRate < 0 || finalRate > 100) throw new InvalidFinalRateException () ;
        if (quota <= 0) throw new InvalidQuotaException () ;
        if (isUniqueLessonName(name)) {
            int uid = generator.generatorUIDForLesson();
            lessonCreator.addLessonToDb(name, lesson_credit, lessonHour, uid, quota , midtermRate , finalRate);
        }
        else throw new NotUniqueLessonNameException () ;
    }

    private boolean isUniqueLessonName(String name) throws NotUniqueLessonNameException, SQLException {
        for (String currentName : lessonFetchingQuery.getAllLessonNames()) {
            if (currentName.equals(name)) {
                throw new NotUniqueLessonNameException();
            }
        }
        return true;
    }

    @Override
    public Lesson createLessonWhichExistInDb(int lessonUID) throws SQLException {
        ResultSet resultSet = lessonFetchingQuery.getLessonInfo(lessonUID);
        String lessonName = null;
        int lessonCredit = 0;
        int lessonHour = 0;
        int quota = 0;
        int midtermRate = 0 ;
        int finalRate = 0 ;
        while (resultSet.next()) {
            lessonName = resultSet.getString("lesson_name");
            lessonCredit = resultSet.getInt("lesson_credit");
            lessonHour = resultSet.getInt("lesson_hour");
            quota = resultSet.getInt("quota");
            midtermRate = resultSet.getInt("average_midterm_rate");
            finalRate = resultSet.getInt("average_final_rate");
        }
        Lesson lesson = new Lesson(lessonName, lessonCredit, lessonHour, lessonUID, quota , midtermRate , finalRate);
        return lesson;
    }

}
