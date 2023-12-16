/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolmanagement.person;

import java.util.Random;
import schoolmanagement.lesson.Lessons;

/**
 *
 * Bu sinif ile istemci direkt olarak iletisime gecemeyecek 
 * 
 * Bu sinif ile iletisime gececek olan sinif PersonManager sinifidir 
 * 
 * PersonManager sinifi ise istemci ile iletisime gecebilecek
 * 
 * Bu sinifin bir tane nesnesinin olusmasi yeterli
 * 
 * 
 * @author kaan
 */
public class PersonFactory {
    private static PersonFactory factory = null ;
    
    private PassGenerator passGen = null ;
    
    /*
    Bu ozellikleri degistirilmek istenirse kolayca takibi yapilsin diye koydum
    */
    
    private final int defaultStudentDebAmount ;
    
    private final int defaultStudentBalanceAmount ;
    
    private final int defaultWorkingStudentDebAmount ;
    
    private final int defaultWorkingStudentBalanceAmount ;

    
    private final int defualtTeacherBalanceAmount ;
    
    private PersonFactory() {
        passGen = PassGenerator.createPassGenerator() ;
        
        defaultStudentDebAmount = 0 ;
        
        defaultWorkingStudentDebAmount = 0 ;
        
        defaultWorkingStudentBalanceAmount = 200 ;
        
        defualtTeacherBalanceAmount = 500 ;
        
        defaultStudentBalanceAmount = 100 ;
    }
    
    
    //Istemcinin erisememesi icin default access modifier
    static PersonFactory createPersonFactory () {
        if (factory == null) {
            factory = new PersonFactory () ;
        }
        return factory ;
    }
    
    
    WorkingStudent createWorkingStudent(String name, String lastName) {
        int uid = passGen.createUniqueWorkingStudentId();
        return new WorkingStudent(name, lastName, uid , defaultWorkingStudentDebAmount, defaultWorkingStudentBalanceAmount) ;
    }

    Student createStudent(String name, String lastName) {
        int uid = passGen.createUniqueStudentId() ;
        return new Student(name, lastName, uid, defaultStudentDebAmount, defaultStudentBalanceAmount);
    }

    Teacher createTeacher(String name, String lastName,Lessons lesson , int capacity) {
        int uid = passGen.createUniqueTeacherId();
        return new Teacher(name, lastName, uid, defualtTeacherBalanceAmount , lesson, capacity);
    }
    
    /*
    
        Bu iki metot var olan ogrencinin ayni bilgileriyle farkli tip nesne olusturmaya yarayacak
        Yeni id ler de verilecek
    
    */
    
    Student createNormalStudentFromWorkingStudent (WorkingStudent wStudent) {
        int uid = passGen.createUniqueStudentId() ;
        return new Student (wStudent.getName(), wStudent.getLastName(), uid ,wStudent.getDebt(), wStudent.getBalance());
    }
    
    WorkingStudent createWorkingStudentFromNormalStudent (Student student) {
        int uid = passGen.createUniqueWorkingStudentId();
        return new WorkingStudent(student.getName(), student.getLastName(), uid, student.getDebt(), student.getBalance());
    }
    
}
