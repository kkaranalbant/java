/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.exception;

/**
 *
 * @author kaan
 */
public class NotUniqueNameAndLastnameException extends RuntimeException {

    @Override
    public String getMessage() {
        return "This name and lastname pair is already in database !" ;
    }
    
}
