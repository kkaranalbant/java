/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.person;

/**
 *
 * @author kaan
 * 
 */
public class StudentValidation {
    static boolean isValidMaxDebtCredit (int value) {
        return value>=0 ; 
    }
    
    static boolean isValidDefaultLessonCredit (int value) {
        return value > 0 ;
    }
}
