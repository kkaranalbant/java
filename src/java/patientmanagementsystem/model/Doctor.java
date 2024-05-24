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
public class Doctor extends Person {

    private Long id;
    private String branch;
    private String workPlace;

    public Doctor(String branch, String workPlace, String name, String lastName, String phoneNumber, String address, Gender gender, LocalDate birthDate) {
        super(name, lastName, phoneNumber, address, gender, birthDate);
        this.branch = branch;
        this.workPlace = workPlace;
    }

    public Doctor(Long id, String branch, String workPlace, String name, String lastName, String phoneNumber, String address, Gender gender, LocalDate birthDate) {
        super(name, lastName, phoneNumber, address, gender, birthDate);
        this.id = id;
        this.branch = branch;
        this.workPlace = workPlace;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getBranch() {
        return branch;
    }

    public String getWorkPlace() {
        return workPlace;
    }

}
