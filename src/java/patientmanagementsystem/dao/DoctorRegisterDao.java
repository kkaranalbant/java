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
public class DoctorRegisterDao {

    @JsonProperty("name")
    private String name;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("birthDate")
    private LocalDate birthDate;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("address")
    private String address;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("branch")
    private String branch;

    @JsonProperty("workingPlace")
    private String workingPlace;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public DoctorRegisterDao(String name, String lastname, LocalDate birthDate, Gender gender, String address, String phoneNumber, String branch, String workingPlace, String username, String password) {
        this.name = name;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.workingPlace = workingPlace;
        this.branch = branch;
        this.username = username;
        this.password = password;
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

    public String getBranch() {
        return branch;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
