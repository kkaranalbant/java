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
public class MedicalReportUpdatingDao {

    private Long medicalReportId;

    private String context;

    private String labResultUrl;

    private LocalDate date;

    private Long patientId;

    private Long doctorId;

    public MedicalReportUpdatingDao(Long medicalReportId, String context, String labResultUrl) {
        this.medicalReportId = medicalReportId;
        this.context = context;
        this.labResultUrl = labResultUrl;
        this.date = LocalDate.now();
    }

    public MedicalReportUpdatingDao(Long medicalReportId, String context, String labResultUrl, Long patientId, Long doctorId) {
        this.medicalReportId = medicalReportId;
        this.context = context;
        this.labResultUrl = labResultUrl;
        this.patientId = patientId;
        this.doctorId = doctorId;
        date = LocalDate.now();
    }

    public Long getMedicalReportId() {
        return medicalReportId;
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
