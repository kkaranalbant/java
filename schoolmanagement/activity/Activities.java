/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package schoolmanagement.activity;

import java.util.LinkedList;
import java.util.List;
import schoolmanagement.person.Student;

/**
 * Aktivitelerin bulundugu enum 
 * Her bir aktivitenin kapasitesi ve katildigi ogrencilerin tutuldugu listesi var bu iki ozellikte sonradan degistirilemeyecek sekilde ayarlandi.
 * @author kaan
 */
public enum Activities {

    SPRING_FESTIVAL(100),
    SUMMER_FESTIVAL(200),
    WINTER_FESTIVAL(100),
    AUTUMN_FESTIVAL(150);

    private final int capacity;
    private final List<Student> rollCall;

    private Activities(int capacity) {
        this.capacity = capacity;
        rollCall = new LinkedList();
    }

    
    /**
     * 
     * Kullanicin erismemesi ve Activity manager sinifinin erisebilmesi icin default erisim belirteci kullandim
     *  
     */
    
    int getCapacity() {
        return capacity;
    }

    List<Student> getRollCall() {
        return rollCall;
    }

}
