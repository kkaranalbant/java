/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.dataaccess.query;

/**
 *
 * @author kaan
 * 
 */
public class AdminValidationQueryStringCreator {
    public static String createQueryString (String tableName) {
        return "select * from "+tableName ;
    }
}
