/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package equipments;

/**
 *
 * @author kaan
 */
public abstract class Equipment {
    
    private String id ;
    
    private float money ;
    
    Equipment (String id , float money) {
        
        this.id = id ;
        
        this.money = money ;
        
    }
    
    public String getId () {
        
        return id ;
        
    }
    
    public float getMoney () {
        
        return money ;
        
    }
    
}
