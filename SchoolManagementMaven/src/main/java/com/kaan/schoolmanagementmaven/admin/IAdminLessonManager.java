/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.admin;

import java.sql.SQLException;
import com.kaan.schoolmanagementmaven.exception.InvalidFinalRateException;
import com.kaan.schoolmanagementmaven.exception.InvalidLessonCreditException;
import com.kaan.schoolmanagementmaven.exception.InvalidMidtermRateException;
import com.kaan.schoolmanagementmaven.exception.InvalidQuotaException;
import com.kaan.schoolmanagementmaven.exception.InvalidUIDException;
import com.kaan.schoolmanagementmaven.exception.NotUniqueLessonNameException;
import com.kaan.schoolmanagementmaven.exception.ReachedMaximumRowNumberException;

/**
 *
 * @author kaan
 * 
 */
public interface IAdminLessonManager {
    public void addLesson (String name , int lesson_credit , int lessonHour , int quota , int midtermRate , int finalRate) throws SQLException, NotUniqueLessonNameException , InvalidQuotaException , InvalidLessonCreditException , InvalidFinalRateException , InvalidMidtermRateException , ReachedMaximumRowNumberException;
    public void removeLesson (int uid) throws InvalidUIDException , SQLException;
}
