/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception ;

/**
 *
 * @author kaan
 */
public class UniqueNameException extends Exception{
    
    @Override
    public void printStackTrace () {
        
        System.out.println("Please choose an unique player name.");
        
    }
    
}
