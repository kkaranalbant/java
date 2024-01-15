/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;

/**
 *
 * @author kaan
 * 
 */
public class LessonCreatingQuery extends Query implements ILessonCreatingQuery {

    private static ILessonCreatingQuery creator;

    private LessonCreatingQuery() throws SQLException{

    }

    static {
        creator = null;
    }

    public static ILessonCreatingQuery getInstance() throws SQLException{
        if (creator == null) {
            creator = new LessonCreatingQuery();
        }
        return creator;
    }

    @Override
    public void addLessonToDb(String lessonName, int lessonCredit, int lessonHour, int lessonUID, int quota , int midtermRate , int finalRate) throws SQLException {
        String query = getAddingLessonToDbQueryString(lessonName, lessonCredit, lessonHour, lessonUID, quota, midtermRate, finalRate) ;
        super.runUpdatingQuery(query);
    }
    
    private String getAddingLessonToDbQueryString (String lessonName, int lessonCredit, int lessonHour, int lessonUID, int quota , int midtermRate , int finalRate) {
        return "insert into " + super.getAccess().getLessonTable() + " values ('" + lessonName + "'," + lessonCredit + "," + lessonHour + "," + lessonUID + "," + quota + ","+midtermRate+ ","+ finalRate +") ;";
    }

}
