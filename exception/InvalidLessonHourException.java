/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.exception;

/**
 *
 * @author kaan
 */
public class InvalidLessonHourException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Lesson Hour mus be bigger than 0";
    }
    
}
