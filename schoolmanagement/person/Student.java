/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolmanagement.person;

import java.util.LinkedList;
import java.util.List;
import schoolmanagement.activity.Activities;
import schoolmanagement.activity.ActivityManager;
import schoolmanagement.lesson.Lessons;

/**
 *
 * Bir ogrenciden etkinlige katilma ve etkinlikten ayrilma davranislari
 * beklenebilir Bir ogrenciden borc odeme ve borc alma davranislari beklenebilir
 * Bir ogrenciden ders secme ve ders silme davranislari beklenebilir Bu 3
 * turdeki sorumlulugu birbirinden ayirmak icin 3 adet yardimci sinif kullanip
 * bu yardimci siniflar icin interface olusuturup daha sonra student nesnesi yardimci siniflara soyut bagli olsa 
 * bu sorumluluklari student nesnesi bu sefer yardimci sinifin nesnelerine yaptirmis olacak 
 * normalde bu islevleri kendi icerisinde yapabilecekken baska nesnelere bagimli olmasi mantiksiz geldigi icin yapmadim
 * ayrica yardimci siniflarin nesneleri de islevlerini yerine getirebilmek icin student nesnesinin ozelliklerine ihtiyac duyacak 
 * (mesela borc odeme islevi icin borc ozelliginin kontrol edilmesi gibi)
 * 
 * fabrika sinifinda ogrenci nesnesini uretilirken baslangicta ogrencinin
 * kredisi DEFAULT_LESSON_CREDIT_NUMBERA esitlenecek bundan dolayi erisim
 * belirteci default MAX_DEBT_CREDIT sabiti ise ogrencinin alabilecegi maksimum
 * borc verisini tutuyor
 *
 * lessonCredit degiskeninde ogrencinin ders kredisi bilgisi tutuluyor.
 *
 * @author kaan
 */
public class Student extends Person {

    static final int DEFAULT_LESSON_CREDIT_NUMBER = 40;
    private static final int MAX_DEBT_CREDIT = 1000;

    private final List<Lessons> lessonList;
    private int debt;
    private int lessonCredit;

    Student(String name, String lastName, int uid, int debt, int balance) {
        super(name, lastName, uid, balance);
        this.debt = debt;
        this.lessonCredit = DEFAULT_LESSON_CREDIT_NUMBER;
        lessonList = new LinkedList();
    }

    /*
        Ogrencinin etkinlige katilmak icin kullanacagi islev etkinlige katilma kontrolleri ActivityManager sinifinda yapiliyor
    */
    
    public void joinActivity(Activities activity) {
        boolean added = ActivityManager.addStutentToActivity(this, activity);
        if (added) {
            System.out.println("Successful process .");
        } else {
            System.out.println("Failure process.");
        }
    }

    /*
        Ogrencinin etkinlikten ayrilmak icin kullanacagi islev . Kontroller ActivityManager sinifinda yapiliyor
    */
    
    public void cancelParticipation(Activities activity) {
        boolean studentRemovedFromActivity = ActivityManager.removeStudentFromActivity(this, activity);
        if (studentRemovedFromActivity) {
            System.out.println("Successful process.");
        } else {
            System.out.println("Failure process.");
        }
    }
    
    /*
        Ogrencinin borc odemek icin kullanacagi islev
    */

    public void payDebt(int value) {
        if (isValidDebtValue(value)) {
            debt -= value;
        } else {
            System.out.println("Invalid value entry detected.");
        }
    }
    
    private boolean isValidDebtValue(int value) {
        if (value <= 0 || value > debt) {
            return false;
        }
        return true;
    }
    
    /*
        Ogrencinin borc almasi . Borcu artar ve aldigi borc hesabina yuklenir . Maksimum alabilecegi borctan fazla borclanamaz
    */
    

    public void getLoan(int value) {
        if (value > 0 && value <= MAX_DEBT_CREDIT) {
            debt += value;
            super.setBalance(super.getBalance()+value);    // bir sinifin ust sinifinin metodunu kullanmak bagimlilik olarak geciyor mu ? 
            //sonucta o metot ust sinifta yazilmista olsa bile sub classta o metoda sahip. Kendi davranisini kullanmak bagimlilik olarak sayiliyor mu.
            System.out.println("Successful");
            return;
        }
        System.out.println("Failure.");
    }
    
    
    
    
    
    
    /*
        Burada nesnenin calisan ogrenci olup olmadigini kontrol etsem bile aslinda yanlis bir tasarim var 
        Cunku calisan ogrenci tipinini burada kontrol etsek bile calisan ogrencinin calisan ogrenci olma davranisi var
        Bunu nasil cozebilirim ? 
    */
    public WorkingStudent beWorkingStudent () {
        WorkingStudent wStudent = null ;
        if (this instanceof WorkingStudent) {
            System.out.println("You are already a working student.");
        }
        else {
            PersonManager manager = PersonManager.createPersonManager() ; // PersonManager sinifinin tek nesnesi olusabildigi icin tekrar tekrar nesne uretilmiyor
            wStudent = manager.convertNormalStudentToWorkingStudent(this);
        }
        return wStudent ;
    }
    
    
    
    
    
    
    
    

    /**
     * Ogrenci hangi dersi hangi ogretmenden almak istedigini seciyor
     */
    
    public void addLesson(Lessons lesson , Teacher teacher) {
        boolean validSpeciality = isValidSpeciality(lesson, teacher) ;
        boolean takenLesson = takenLesson(lesson);
        if (!takenLesson && hasSufficentCredit(lesson) && validSpeciality) {
            if(teacher.isStudentAddedToList(this)) {
                lessonCredit -= lesson.getLessonCredit() ;
                System.out.println("Successful process.");
                return ;
            }   
            else {
                System.out.println("Quota full.");
            }
        }
        System.out.println("Failure process.");
    }
    
    
    
    
    
    /*
        Ogrencinin sectigi ders ve ogretmenin bransinin ayni olup olmadigini kontrol ediyor .
    */
    private boolean isValidSpeciality (Lessons lesson , Teacher teacher) {
        if (lesson == teacher.getSpeciality()) return true ;
        return false ;
    }
    
    /*
        Ogrencinin dersi daha onceden secip secmediginin kontrolu
    */

    private boolean takenLesson(Lessons lesson) {
        for (Lessons currentLesson : lessonList) {
            if (currentLesson == lesson) {
                return true;
            }
        }
        return false;
    }
    
    /*
        Ogrencinin ders secmesi icin yeterli kredisinin olup olmadiginin kontrolu
    */
    
    private boolean hasSufficentCredit (Lessons lesson) {
        if (lesson.getLessonCredit() > lessonCredit) return false ;
        return true ;
    }

    // PersonManager nesnesinin kullanmaya ihtiyaci var
    int getDebt () {
        return debt ;
    }

}
