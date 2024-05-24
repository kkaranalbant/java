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
public class ElementIdDao {

    @JsonProperty("id")
    private Long id;

    public ElementIdDao(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
