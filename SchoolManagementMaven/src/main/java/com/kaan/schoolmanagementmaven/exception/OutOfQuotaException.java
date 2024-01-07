/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.exception;

/**
 *
 * @author kaan
 */
public class OutOfQuotaException extends RuntimeException{

    @Override
    public String getMessage() {
        return "You can not choose this lesson .\nAttendance number is full." ;
    }
    
}
