/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package places;

import java.util.Random;

/**
 *
 * @author kaan
 */
public abstract class Place {
    
    int enemyNumber ;
    
    Random creator = new Random () ;
    
    public abstract void createEnemy () ;
    
    public int getEnemyNumber () {
        
        return enemyNumber ;
        
    }
    
    
    
}
