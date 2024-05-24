/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.model;

import java.time.LocalDate;

/**
 *
 * @author kaan
 */
public class Patient extends Person {

    private Long id;

    public Patient(String name, String lastName, String phoneNumber, String address, Gender gender, LocalDate birthDate) {
        super(name, lastName, phoneNumber, address, gender, birthDate);
    }

    public Patient(Long id, String name, String lastName, String phoneNumber, String address, Gender gender, LocalDate birthDate) {
        super(name, lastName, phoneNumber, address, gender, birthDate);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
