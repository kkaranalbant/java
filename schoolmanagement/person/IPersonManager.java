/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package schoolmanagement.person;

import schoolmanagement.lesson.Lessons;

/**
 *
 * @author kaan
 */
public interface IPersonManager {
    public Student addNormalStudentToList (String name , String lastName) throws NullPointerException ;
    public WorkingStudent addWorkingStudentToList (String name , String lastName) throws NullPointerException;
    public Teacher addTeacherToList (String name , String lastName , Lessons lesson , int capacity) throws NullPointerException ;
    public <T extends Person> void removePersonFromList (T person) throws NullPointerException;
}
