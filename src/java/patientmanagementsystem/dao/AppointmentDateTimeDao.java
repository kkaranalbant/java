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
public class AppointmentDateTimeDao {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("date")
    private String date;

    @JsonProperty("time")
    private String time;

    public AppointmentDateTimeDao(Long id, String date, String time) {
        this.id = id;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
    
    

}
