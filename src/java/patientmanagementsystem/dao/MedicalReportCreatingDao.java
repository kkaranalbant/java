/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.dao;

import java.time.LocalDate;

/**
 *
 * @author kaan
 */
public class MedicalReportCreatingDao {

    private Long patientId;

    private Long doctorId;

    private String context;

    private String labResultUrl;

    private LocalDate date;

    public MedicalReportCreatingDao(Long patientId, Long doctorId, String context, String labResultUrl) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.context = context;
        this.labResultUrl = labResultUrl;
        this.date = LocalDate.now();
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public String getContext() {
        return context;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLabResultUrl() {
        return labResultUrl;
    }

}
