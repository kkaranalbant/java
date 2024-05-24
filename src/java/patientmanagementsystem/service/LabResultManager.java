/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.service;

import java.sql.SQLException;
import java.util.List;
import patientmanagementsystem.model.LabResult;
import patientmanagementsystem.repository.LabResultRepo;

/**
 *
 * @author kaan
 */
public class LabResultManager {

    private static LabResultManager labResultManager;

    private LabResultRepo labResultRepo;

    private LabResultManager() throws SQLException, ClassNotFoundException {
        labResultRepo = LabResultRepo.getInstance();
    }

    public static LabResultManager getInstance() throws SQLException, ClassNotFoundException {
        if (labResultManager == null) {
            labResultManager = new LabResultManager();
        }
        return labResultManager;
    }

    public void saveLabResult(Long medicalReportId, String url) throws SQLException {
        LabResult oldLabResult = labResultRepo.getLabResultByMedicalReportIdAndUrl(medicalReportId, url);
        if (oldLabResult != null) {
            oldLabResult.setContextUrl(url);
            labResultRepo.saveLabResult(oldLabResult);
            return;
        }
        LabResult labResult = new LabResult(medicalReportId, url);
        labResultRepo.saveLabResult(labResult);
    }
    
    public void saveLabResult (LabResult labResult) throws SQLException {
        labResultRepo.updateLabResult(labResult) ;
    }
    
    public LabResult getLabResultByMedicalReportIdAndUrl (Long medicalReportId , String url) throws SQLException {
        return labResultRepo.getLabResultByMedicalReportIdAndUrl(medicalReportId, url);
    }
    
    public LabResult getLabResultByMedicalReportId (Long medicalReportId) throws SQLException {
        return labResultRepo.getLabResultByMedicalReportId(medicalReportId);
    }

    public void removeLabResultByMedicalReportId(Long medicalReportId) throws SQLException {
        labResultRepo.deleteLabResultByReportIdId(medicalReportId);
    }
    
    public void removeLabResultByResultId(Long resultId) throws SQLException {
        labResultRepo.deleteLabResultByResultId(resultId);
    }
    
    public List <LabResult> getAllLabResults () throws SQLException {
        return labResultRepo.getAllLabResults() ;
    }

}
