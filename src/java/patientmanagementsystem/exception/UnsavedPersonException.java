/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.exception;

/**
 *
 * @author kaan
 */
public class UnsavedPersonException extends BaseException {

    public UnsavedPersonException() {
        super("Person could not save !");
    }

    public UnsavedPersonException(String username , String password) {
        super(Boolean.toString(username == null || password == null));
    }
    
    
    
}
