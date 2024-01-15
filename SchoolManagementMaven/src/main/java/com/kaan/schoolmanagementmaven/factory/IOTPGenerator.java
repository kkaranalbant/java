/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.kaan.schoolmanagementmaven.factory;

/**
 *
 * @author kaan
 * 
 */
public interface IOTPGenerator {
    int OTP_ORIGIN = 100000 ;
    int OTP_BOUND = 1000000 ;
    int generateOTP () ;
}
