/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author kaan
 * 
 */
public interface IPersonConvertingQuery {

    public void convertToNormalStudent(String userName, String pass, String name, String lastName, int uid, int newUid, int balance, int debt, int lessonCredit, Map <Integer , Integer> lessonTeacherMap , String phoneNumber) throws SQLException;

    public void convertToWorkingStudent(String userName, String pass, String name, String lastName, int uid, int newUid, int balance, int debt, int lessonCredit, Map <Integer , Integer> lessonTeacherMap , String phoneNumber) throws SQLException;
}
