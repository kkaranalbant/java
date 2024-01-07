/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.lesson;

/**
 *
 * @author kaan
 */
public class Lesson {
    private String lesson_Name ;
    private int lessonCredit ; 
    private int lessonHour ;
    private int lessonUID ;
    private int quota ;
    private int midtermRate ;
    private int finalRate ;

    public Lesson(String lesson_Name, int lessonCredit, int lessonHour, int lessonUID, int quota , int midtermRate , int finalRate) {
        this.lesson_Name = lesson_Name;
        this.lessonCredit = lessonCredit;
        this.lessonHour = lessonHour;
        this.lessonUID = lessonUID;
        this.quota = quota;
        this.midtermRate = midtermRate ;
        this.finalRate = finalRate ;
    }
    
    public int getLessonCredit () {
        return lessonCredit ;
    }
    
    public void display () {
        System.out.println(lesson_Name);
        System.out.println(lessonCredit);
        System.out.println(lessonHour);
        System.out.println(lessonUID);
        System.out.println(quota);
    }
    
    public String getName () {
        return lesson_Name ;
    }
    
}
