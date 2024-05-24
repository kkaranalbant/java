/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.model;

import java.time.LocalDateTime;

/**
 *
 * @author kaan
 */
public class Appointment {
    
    private Long id ;
    private Long patientId ;
    private Long doctorId ;
    private LocalDateTime appointmentTime ;
    
    public Appointment (Long id ,Long patientId, Long doctorId , LocalDateTime appointmentTime) {
        this.id = id ;
        this.patientId = patientId ;
        this.doctorId = doctorId ;
        this.appointmentTime = appointmentTime ;
    }

    public Appointment(Long patientId, Long doctorId, LocalDateTime appointmentTime) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentTime = appointmentTime;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }
    
    
    
    
}
