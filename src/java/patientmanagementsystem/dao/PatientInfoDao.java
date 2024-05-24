/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.dao;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import patientmanagementsystem.model.Gender;

/**
 *
 * @author kaan
 */
public class PatientInfoDao {
    
    @JsonProperty("id") 
    private Long id ;
    
    @JsonProperty("name")
    private String name ;
    
    @JsonProperty("lastname")
    private String lastname ;
    
    @JsonProperty("birthDate")
    private LocalDate birthDate ;
    
    @JsonProperty("gender")
    private Gender gender ;
    
    @JsonProperty("address")
    private String address ;
    
    @JsonProperty("phoneNumber")
    private String phoneNumber ;

    public PatientInfoDao(Long id , String name, String lastname, LocalDate birthDate, Gender gender, String address, String phoneNumber) {
        this.id = id ;
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Long getId() {
        return id;
    }
    
    
    
}
