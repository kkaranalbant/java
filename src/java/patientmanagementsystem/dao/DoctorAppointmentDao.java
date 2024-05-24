/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.dao;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author kaan
 */
public class DoctorAppointmentDao {
    
    @JsonProperty("id")
    private Long id ;
    
    @JsonProperty("name")
    private String name ;
    
    @JsonProperty("surname")
    private String lastname ;

    public DoctorAppointmentDao(Long id , String name, String lastname) {
        this.id = id ;
        this.name = name;
        this.lastname = lastname;
    }
    
    
    
}
