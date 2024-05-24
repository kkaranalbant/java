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
public class LoginDao {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public LoginDao(String username, String password) {
        this.username = username;
        this.password = password ;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
