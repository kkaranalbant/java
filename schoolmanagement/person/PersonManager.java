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
 * Bu sinifinin nesnesinden bir tane olusmasi yeterli 
 * 
 * Bu sinifin nesnesi istemci ile iletisime gececek
 * 
 * 
 * @author kaan
 */
public class PersonManager implements IPersonManager {
    static final List <Student> normalStudentList = new LinkedList ();
    static final List <WorkingStudent> workingStudentList = new LinkedList ();
    static final List <Teacher> teacherList = new LinkedList ();
    
    private static PersonManager manager = new PersonManager () ;
    
    private PersonFactory factory ;
    
    private PersonManager () {
        factory = PersonFactory.createPersonFactory() ;
    }
    
    public static PersonManager createPersonManager () {
        if (manager == null) {
            manager = new PersonManager() ;
        }
        return manager ;
    }
    
    @Override
    public Student addNormalStudentToList (String name , String lastName) throws NullPointerException{
        Student student = null ;
        if (isUniqueNameAndLastName(name, lastName, normalStudentList)) {
            student = factory.createStudent(name, lastName) ;
        }
        if (student == null) {
            NullPointerException ex = new NullPointerException ();
            throw ex ;
        }
        return student ;
    }
    
    @Override
    public WorkingStudent addWorkingStudentToList (String name , String lastName) throws NullPointerException{
        WorkingStudent wStudent = null ;
        if (isUniqueNameAndLastName(name, lastName, workingStudentList)) {
            wStudent = factory.createWorkingStudent(name, lastName);
        }
        if (wStudent == null) {
            NullPointerException ex = new NullPointerException ();
            throw ex ;
        }
        return wStudent ;
    }
    
    @Override
    public Teacher addTeacherToList (String name , String lastName , Lessons lesson , int capacity) throws NullPointerException{
        Teacher teacher = null ;
        if (isUniqueNameAndLastName(name, lastName, teacherList)) {
            teacher = factory.createTeacher(name, lastName, lesson, capacity);
        }
        if (teacher == null) {
            NullPointerException ex = new NullPointerException ();
            throw ex ;
        }
        return teacher ;
    }
    
    private <T extends Person> boolean isUniqueNameAndLastName (String name , String lastName , List <T> list) {
        for (T currentPerson : list) {
            if (currentPerson.getName().equals(name) && currentPerson.getLastName().equals(lastName)) return false ;
        }
        return true ;
    }
    
    /*
        Silinecek objenin hangi tipte oldugunu istemciden istemek yerine sadece nesneyi istedim 
        Kontrolu bu metodun icerisinde yaptim isPersonInTheList metodu ile beraber calisiyor
        Istemcinin null girebilecegi bir durum oldugu icin NullPointerExcetion firlatiliyor
    */
    
    @Override
    public <T extends Person> void removePersonFromList (T person) throws NullPointerException{
        if (person == null) {
            NullPointerException ex = new NullPointerException ();
            throw ex ;
        }
        if (person instanceof WorkingStudent) {           
            boolean personInTheList = isPersonInTheList(person, workingStudentList) ;
            if (personInTheList) {
                workingStudentList.remove(person);
                return ;
            }
            System.out.println("The person is not in the list.");
        }
        else if (person instanceof Student) {
            boolean personInTheList = isPersonInTheList(person, normalStudentList);
            if(personInTheList) {
                normalStudentList.remove(person);
                return ;
            }
            System.out.println("The person is not in the list.");
        }
        else {
            boolean personInTheList = isPersonInTheList(person, teacherList);
            if (personInTheList) {
                teacherList.remove(person);
                return ;
            }
            System.out.println("The person is not in the list.");
        }
    }
    
    /*
        WorkingStudent sinifindan erisilebilecek bir metot o yuzden default constructor
        Calisan ogrenci calismak istemiyorsa bu metot once factory nesnesininin islevini kullanarak ogrencinin var olan bilgileriyle Normal ogrenci tipi olusturtmaya yarayacak
        Daha sonra ise calisan ogrenci nesnesi calisan ogrenci listesinden kaldirilicak
        Yeni olusturulan normal ogrenci ise normal ogrenci listesine eklenecek
    */
    
    Student convertWorkingStudentToNormalStudent (WorkingStudent wStudent) {
        Student newStudent = factory.createNormalStudentFromWorkingStudent(wStudent);
        workingStudentList.remove(wStudent);
        normalStudentList.add(newStudent);
        return newStudent ;
    }
    
    WorkingStudent convertNormalStudentToWorkingStudent (Student student) {
        WorkingStudent newStudent = factory.createWorkingStudentFromNormalStudent(student);
        normalStudentList.remove(student);
        workingStudentList.add(newStudent);
        return newStudent ;
    }
    
    
    /*
        nesnenin listede olup olmadigini kontrol ediyor
    */
    
    private <T extends Person> boolean isPersonInTheList (T person , List<? extends Person> list) {
        for (Person currentPerson : list) {
            if (currentPerson == person) return true ;
        }
        return false ;
    }
        
}
