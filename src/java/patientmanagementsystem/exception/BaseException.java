/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.exception;

/**
 *
 * @author kaan
 */
public abstract class BaseException extends RuntimeException{
    private String message ;
    
    BaseException (String message) {
        this.message = message ;
    }
    
    @Override
    public String getMessage () {
        return message ;
    }
}
