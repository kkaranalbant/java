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
public class MedicalReport {

    private Long id;

    private Long patientId;

    private Long doctorId;

    private LocalDate publicationDate;

    private String context;

    public MedicalReport(Long id , LocalDate publicationDate, String context, Long patientId, Long doctorId) {
        this.id = id ;
        this.publicationDate = publicationDate ;
        this.patientId = patientId ;
        this.doctorId = doctorId ;
        this.context = context ;
    }

    public MedicalReport(Long patientId, Long doctorId, LocalDate publicationDate, String context) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.publicationDate = publicationDate;
        this.context = context;
    }

    public MedicalReport(Long id, LocalDate publicationDate, String context) {
        this.id = id;
        this.publicationDate = publicationDate;
        this.context = context;
    }
    
    

    public Long getId() {
        return id;
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

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
    
    

}
