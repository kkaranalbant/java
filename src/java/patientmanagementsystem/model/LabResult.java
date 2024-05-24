/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.model;

/**
 *
 * @author kaan
 */
public class LabResult {
    
    private Long id ;
    
    private Long medicalReportId ;
    
    private String contextUrl ;
    
    public LabResult (Long medicalReportId , String contextUrl) {
        this.medicalReportId = medicalReportId ;
        this.contextUrl = contextUrl;
    }

    public LabResult(Long id, Long medicalReportId, String contextUrl) {
        this.id = id;
        this.medicalReportId = medicalReportId;
        this.contextUrl = contextUrl;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicalReportId() {
        return medicalReportId;
    }

    public void setMedicalReportId(Long medicalReportId) {
        this.medicalReportId = medicalReportId;
    }

    public String getContextUrl() {
        return contextUrl;
    }

    public void setContextUrl(String contextUrl) {
        this.contextUrl = contextUrl;
    }
    
    
    
}
