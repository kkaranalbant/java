/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolmanagement.activity;

import java.util.List;
import schoolmanagement.person.Student;

/**
 *
 * Utility Class . Nesnesinin olusmasina ihtiyac yok. Cunku islevlerinin calismasi icin ozellige ihtiyaci yok.
 * @author kaan
 */
public class ActivityManager {

    private ActivityManager() {
    }
    
    /**
     * 
     * 
     * Normalde bu iki metoda sadece ogrenci nesnesi uzerinden erismek istiyordum
     * Cunku kullanicinin bu iki metottan soyutlanmasini daha mantikli buldum
     * Bu iki metodu default yaparsam bu sefer ogrenci sinifi da farkli pakette oldugu icin erisemeyecegim
     * 
     * Ancak activitymanager sinifi ile ogrenci sinifinin amaclari birbirinden farkli olduklari icin ayni pakete yerlestirmeyi de dogru bulmadim 
     * Bu yuzden ogrenci sinifindan bu metotlara erisebilmek icin erisim belirtecini public yaptim bundan dolayi kullanici da bu metotlara erisebiliyor
     * 
     * Bu sinifi ogrenci sinifi ile ayni pakete koymadan hem bu metodu kullanicidan soyutlayip hem de ogrenci nesnesi uzerinden nasil erisebilirim ? 
     * 
     */
    
    public static boolean addStutentToActivity(Student student, Activities activity) {
        List<Student> rollCall = activity.getRollCall();
        if (!isStudentInTheList(student, rollCall) && !isLimitUp(activity)) {
            rollCall.add(student);
            return true;
        }
        return false;
    }

    public static boolean removeStudentFromActivity(Student student, Activities activity) {
        List<Student> rollCall = activity.getRollCall();
        if (isStudentInTheList(student, rollCall)) {
            rollCall.remove(student);
            return true;
        }
        return false;
    }

    private static boolean isStudentInTheList(Student student, List<Student> rollCall) {
        for (Student currentStudent : rollCall) {
            if (currentStudent == student) {
                return true;
            }
        }
        return false;
    }

    private static boolean isLimitUp(Activities activity) {
        if (activity.getCapacity() == activity.getRollCall().size()) {
            return true;
        }
        return false;
    }

}
