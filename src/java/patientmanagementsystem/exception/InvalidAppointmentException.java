/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.exception;

/**
 *
 * @author kaan
 */
public class InvalidAppointmentException extends BaseException{
    
    
    public InvalidAppointmentException () {
        super("Please enter a valid appointment !") ;
    }
    
    
}
