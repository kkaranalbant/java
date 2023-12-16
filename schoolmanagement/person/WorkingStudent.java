/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolmanagement.person;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * Calisan ogrenci calisma davranisina sahip 
 * olacagindan student sinifinda boolean degisken 
 * ile calisip calismadigini belirten bir ozellik
 * tanimlayip o ozelligin degerine gore calisma metodunun calisip calismamasini istemedim
 * Cunku eger oyle yapsaydim aslinda butun ogrencilerin calisma davranisina sahip olmalari gerekirdi
 * 
 * 
 * Ogrencilerin saat basina aldiklari para costForPerHour ile  maksimum calisma saati maxWorkingHour ile calisan ogrencilerin bilgisi ise workingStudentList ile tutulacak
 * 
 * 
 * @author kaan
 */
public class WorkingStudent extends Student{

    private static final int MAX_WORKING_HOUR = 8 ;
    
    private static final int COST_FOR_PER_HOUR = 10 ;
    
    WorkingStudent(String name, String lastName, int uid , int debt , int balance) {
        super(name, lastName, uid , debt , balance);
        //workingStudentList.add(this); bunu yapma hala constructor icerisinden nesne tam olusturulmamis olabilir. Bu kullanim best practice degil.
        //Onun yerine fabrika sinifinda nesneyi olusturunca daha sonra listeye eklemeyi orada yap daha guvenli.
    }
    
    public void work (int hour) {
        boolean validHour = hourControl(hour);
        if (validHour) {
            int value = hour * COST_FOR_PER_HOUR ;
            super.setBalance(super.getBalance() + value);
            System.out.println("Successful.");
        }
        else {
            System.out.println("Invalid hour entry detected.");
        }
    }
    /*
        //fabrika metodu cagrilarak ilgili ozellikler verilir ve yeni bir ogrenci nesnesi olusturulur . var olan calisan ogrenci nesnesi listeden silinir.
    */
    public Student dontWorkAnymore () {
        PersonManager manager = PersonManager.createPersonManager() ; // var olan nesne donecek yeni olusmayacak
        Student student = manager.convertWorkingStudentToNormalStudent(this);
        return student ;
    }
    
    private boolean hourControl (int hour) {
        if (hour>MAX_WORKING_HOUR || hour <= 0) return false ;
        return false ;
    }
    
}
