/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolmanagement.person;

import java.util.LinkedList;
import java.util.List;
import schoolmanagement.lesson.Lessons;

/**
 *
 * speciality nesnesi ogretmenin bransidir
 * 
 * ogretmen , dersini alan ogrencilerin listesine sahiptir 
 * 
 * ogrenci ders secimi yaptigi zaman ogretmenin listesine eklenmis olur 
 * 
 * ogretmenin listesinin bir kapasitesi vardir ogrenci dersi secse bile eklenmeyebilir cunku kontenjan dolmus olabilir .
 * 
 * @author kaan
 */
public class Teacher extends Person implements ITeacher{
    
    private static final int COST_FOR_PER_LESSON_HOUR = 20 ;
    
    private final int capacity ;

    private final Lessons speciality;
    
    private final List <Student> studentList ;

    public Teacher(String name, String lastName, int uid, int balance , Lessons speciality , int capacity) {
        super(name, lastName, uid, balance);
        this.speciality = speciality ;
        studentList = new LinkedList () ;
        this.capacity = capacity ;
    }

    /*
        Ogretmenin listesindeki ogrencilere ders vermesi icin kullanacagi islev . Ayrica ders anlatimindan kazandigi para da bakiyeye ekleniyor
    */
    @Override
    public void teach () {
        for (Student student : studentList) {
            System.out.println(super.getName()+" "+super.getLastName()+" lecturing : "+student.getName() + " "+student.getLastName());
        }
        super.setBalance( super.getBalance() + speciality.getLessonTime() * COST_FOR_PER_LESSON_HOUR); // ogretmenin anlattigi ders saati basina para kazanmasi icin
    }
    
    /*
        Ogrencinin ogretmendeki listeye eklenmesi icin kullanilan islev 
        Student sinifindaki bazi sartlar saglandiktan sonra ve ayrica kontenjan listesinin dolu  olup olmadigina gore ogrenci listeye ekleniyor .
    */
    
    boolean isStudentAddedToList (Student student) {
        if (isStudentListFull()) {
            return false ;
        }
        addStudentToList(student);
        return true ;
    }
    
    private void addStudentToList (Student student) {
        studentList.add(student);
    }
    
    /*
        Ogrenci sinifindan erisilmesi gerekiyor o yuzden default
    */
    Lessons getSpeciality() {
        return speciality ;
    }
    
    private boolean isStudentListFull () {
        if (studentList.size() == capacity) return true ;
        return false ;
    }
    
}
