/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package schoolmanagement;

import schoolmanagement.activity.Activities;
import schoolmanagement.lesson.Lessons;
import schoolmanagement.person.PersonManager;
import schoolmanagement.person.Student;
import schoolmanagement.person.Teacher;
import schoolmanagement.person.WorkingStudent;

/**
 *
 * @author kaan
 */
public class SchoolManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PersonManager manager = PersonManager.createPersonManager();
        Student student = manager.addNormalStudentToList("kaan", "karanalbant");
        student.joinActivity(Activities.AUTUMN_FESTIVAL);
        student.payDebt(3);
        student.getLoan(500);
        WorkingStudent wStudent = student.beWorkingStudent();
        wStudent.work(5);
        Student student1 = wStudent.dontWorkAnymore();
        Teacher teacher = manager.addTeacherToList("asd", "asd", Lessons.MATH, 30) ;
        student.addLesson(Lessons.MATH, teacher);
        student.cancelParticipation(Activities.AUTUMN_FESTIVAL);
        teacher.teach();
        
    }
    
}
