/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.sms;

/**
 *
 * @author kaan
 * 
 */
public interface IMessageSendingManager {
    void sendMessage (String destinationPhoneNumber , int code) ;
}
