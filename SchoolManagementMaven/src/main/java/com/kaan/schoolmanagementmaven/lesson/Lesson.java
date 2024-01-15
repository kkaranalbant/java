/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.lesson;

/**
 *
 * @author kaan
 * 
 */
public class Lesson {
    private String name ;
    private int credit ; 
    private int hour ;
    private int UID ;
    private int quota ;
    private int midtermRate ;
    private int finalRate ;

    public Lesson(String name, int credit, int hour, int UID, int quota , int midtermRate , int finalRate) {
        this.name = name;
        this.credit = credit;
        this.hour = hour;
        this.UID = UID;
        this.quota = quota;
        this.midtermRate = midtermRate ;
        this.finalRate = finalRate ;
    }
    
    public int getLessonCredit () {
        return credit ;
    }
    
    public String getName () {
        return name ;
    }
    
}
