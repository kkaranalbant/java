/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolmanagement.person;

/**
 *
 * @author kaan
 */
public abstract class Person {
    private String name ;
    private String lastName ; 
    private int uid ;
    private int balance ;
    
    Person (String name , String lastName , int uid , int balance) {
        this.name = name ;
        this.lastName = lastName ;
        this.uid = uid ;
        this.balance = balance ;
    }
    
    void setBalance (int value) {
        balance+=value;
    }
    
    int getBalance () {
        return balance ;
    }
    
    String getName () {
        return name ;
    }
    
    String getLastName () {
        return lastName ;
    }
    
    int getUID () {
        return uid ;
    }
    
}
