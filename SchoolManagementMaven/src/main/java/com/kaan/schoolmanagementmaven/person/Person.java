/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kaan.schoolmanagementmaven.person;

/**
 *
 * @author kaan
 * 
 */
public abstract class Person {
    private String userName ;
    private String pass ;
    private String name ;
    private String lastName ;
    private String phoneNumber ;
    private int uid ;
    private int balance ;

    public Person(String userName , String pass , String name, String lastName, int uid, int balance , String phoneNumber) {
        this.userName = userName ;
        this.pass = pass ;
        this.name = name;
        this.lastName = lastName;
        this.uid = uid;
        this.balance = balance;
        this.phoneNumber = phoneNumber ;
    }

    public String getUserName() {
        return userName;
    }

    public String getPass() {
        return pass;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getBalance() {
        return balance;
    }
    
    public String getPhoneNumber () {
        return phoneNumber ;
    }
    
    
}
