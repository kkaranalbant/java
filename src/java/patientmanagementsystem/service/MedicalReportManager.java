/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patientmanagementsystem.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import patientmanagementsystem.dao.MedicalReportCreatingDao;
import patientmanagementsystem.dao.MedicalReportUpdatingDao;
import patientmanagementsystem.exception.FailureDeletingException;
import patientmanagementsystem.exception.InvalidIdException;
import patientmanagementsystem.model.LabResult;
import patientmanagementsystem.model.MedicalReport;
import patientmanagementsystem.repository.MedicalReportRepo;

/**
 *
 * @author kaan
 */
public class MedicalReportManager {

    private static MedicalReportManager medicalReportManager;

    private MedicalReportRepo medicalReportRepo;
    private LabResultManager labResultManager;

    private MedicalReportManager() throws SQLException, ClassNotFoundException {
        medicalReportRepo = MedicalReportRepo.getInstance();
        labResultManager = LabResultManager.getInstance();
    }

    public static MedicalReportManager getInstance() throws SQLException, ClassNotFoundException {
        if (medicalReportManager == null) {
            medicalReportManager = new MedicalReportManager();
        }
        return medicalReportManager;
    }

    public void saveMedicalReport(MedicalReportCreatingDao medicalReportCreatingDao) throws SQLException {
        MedicalReport medicalReport = new MedicalReport(medicalReportCreatingDao.getPatientId(), medicalReportCreatingDao.getDoctorId(), medicalReportCreatingDao.getDate(), medicalReportCreatingDao.getContext());
        medicalReportRepo.createMedicalReport(medicalReport);
        if (medicalReportCreatingDao.getLabResultUrl() != null) {
            List<MedicalReport> medicalReports = medicalReportRepo.getAllMedicalReports();
            for (MedicalReport medicalReportElement : medicalReports) {
                if (medicalReportElement.getDoctorId() == medicalReportCreatingDao.getDoctorId() && medicalReportElement.getPatientId() == medicalReportCreatingDao.getPatientId()) {
                    labResultManager.saveLabResult(medicalReportElement.getId(), medicalReportCreatingDao.getLabResultUrl());
                }
            }
        }
    }

    public List<MedicalReport> getAllMedicalReports() throws SQLException {
        return medicalReportRepo.getAllMedicalReports();
    }

    public List<MedicalReport> getAllMedicalReportsByPatientId(Long patientId) throws SQLException {
        return medicalReportRepo.getAllMedicalReportsByPatientId(patientId);
    }
    
    public List <MedicalReport> getAllMedicalReportsByDoctorId (Long doctorId) throws SQLException {
        return medicalReportRepo.getAllMedicalReportsByDoctorId(doctorId);
    }

    public void removeMedicalReport(Long medicalReportId, Long doctorId) throws SQLException, FailureDeletingException {
        MedicalReport medicalReport = medicalReportRepo.getMedicalReportById(medicalReportId);
        if (doctorId.longValue() == medicalReport.getDoctorId().longValue()) {
            labResultManager.removeLabResultByMedicalReportId(medicalReportId);
            medicalReportRepo.deleteMedicalReport(medicalReportId);
            return;
        }
        throw new FailureDeletingException();
    }

    public Long getDoctorIdByReportId(Long medicalReportId) throws InvalidIdException, SQLException {
        MedicalReport medicalReport = medicalReportRepo.getMedicalReportById(medicalReportId);
        if (medicalReport == null) {
            throw new InvalidIdException();
        }
        return medicalReport.getDoctorId();
    }

    public void updateMedicalReport(MedicalReportUpdatingDao medicalReportUpdatingDao) throws SQLException {
        String url = medicalReportUpdatingDao.getLabResultUrl() ;
        if (url.equals("")) {
            List <LabResult> results = labResultManager.getAllLabResults() ;
            for (LabResult result : results) {
                if (result.getMedicalReportId().longValue() == medicalReportUpdatingDao.getMedicalReportId().longValue()) {
                    labResultManager.removeLabResultByResultId(result.getId());
                }
            }
        } else {
            List <LabResult> results = labResultManager.getAllLabResults() ;
            for (LabResult result : results) {
                if (result.getMedicalReportId().longValue() == medicalReportUpdatingDao.getMedicalReportId().longValue()) {
                    result.setContextUrl(url);
                    labResultManager.saveLabResult(result);
                }
            }            
        }
        MedicalReport medicalReport = new MedicalReport(medicalReportUpdatingDao.getMedicalReportId(), medicalReportUpdatingDao.getDate(), medicalReportUpdatingDao.getContext());

        medicalReportRepo.updateMedicalReport(medicalReport);
    }

    public void updateMedicalReportByAdmin(Long reportId, Long doctorId, Long patientId, String context, String url) throws SQLException, InvalidIdException {
        if (url.equals("")) {
            List <LabResult> results = labResultManager.getAllLabResults() ;
            for (LabResult result : results) {
                if (result.getMedicalReportId().longValue() == reportId.longValue()) {
                    labResultManager.removeLabResultByResultId(result.getId());
                }
            }
        } else {
            List <LabResult> results = labResultManager.getAllLabResults() ;
            for (LabResult result : results) {
                if (result.getMedicalReportId().longValue() == reportId.longValue()) {
                    result.setContextUrl(url);
                    labResultManager.saveLabResult(result);
                }
            }
        }
        MedicalReport medicalReport = new MedicalReport(reportId, LocalDate.now(), context, patientId, doctorId) ;
        medicalReportRepo.updateMedicalReport(medicalReport);
    }

    public void deleteAllMedicalReportsByPatientId(Long patientId) throws SQLException {
        medicalReportRepo.deleteAllMedicalReportsByPatientId(patientId);
    }

    public void deleteAllMedicalReportsByDoctorId(Long doctorId) throws SQLException {
        medicalReportRepo.deleteAllMedicalReportsByDoctorId(doctorId);
    }

}
