/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package schoolmanagement.lesson;

/**
 *
 * @author kaan
 */
public enum Lessons {
    MATH (6,6),
    SYSTEM_PROGRAMMING (5,6),
    OPERATING_SYSTEMS (5,5),
    NETWORK_FUNDAMENTALS (4,3),
    SOFTWARE_ARCHITECTURE (4,3),
    DATABASE_MANAGEMENT (3,3);    
    
    private int lessonTime ;
    private int lessonCredit ;
    
    private Lessons (int lessonTime , int lessonCredit) {
        this.lessonTime = lessonTime ;
        this.lessonCredit = lessonCredit ;
    }
    
    public int getLessonTime () {
        return lessonTime ;
    }
    
    public int getLessonCredit () {
        return lessonCredit ;
    }
}
