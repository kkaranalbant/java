/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package patientmanagementsystem.controller;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import patientmanagementsystem.dao.ElementIdDao;
import patientmanagementsystem.dao.MedicalReportCreatingDao;
import patientmanagementsystem.dao.MedicalReportUpdatingDao;
import patientmanagementsystem.dao.PatientInfoDao;
import patientmanagementsystem.exception.FailureDeletingException;
import patientmanagementsystem.exception.FailureUpdatingException;
import patientmanagementsystem.exception.InvalidNameOrLastnameException;
import patientmanagementsystem.model.LabResult;
import patientmanagementsystem.model.MedicalReport;
import patientmanagementsystem.model.Patient;
import patientmanagementsystem.service.LabResultManager;
import patientmanagementsystem.service.LoginManager;
import patientmanagementsystem.service.MedicalReportManager;
import patientmanagementsystem.service.PatientManager;

/**
 *
 * @author kaan
 */
@WebServlet(name = "DoctorMedicalReportController", urlPatterns = {"/doctor-report"})
public class DoctorMedicalReportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession(false).getAttribute("role").equals("DOCTOR")) {
            String username = (String) request.getSession(false).getAttribute("username");
            String password = (String) request.getSession(false).getAttribute("password");
            StringBuilder sb = new StringBuilder();
            try {
                Long doctorId = LoginManager.getInstance().getDoctorIdByUsernameAndPassword(username, password);
                List<MedicalReport> reports = MedicalReportManager.getInstance().getAllMedicalReportsByDoctorId(doctorId);
                for (MedicalReport medicalReport : reports) {
                    PatientInfoDao info = PatientManager.getInstance().showPatient(new ElementIdDao(medicalReport.getPatientId()));
                    LabResult labResult = LabResultManager.getInstance().getLabResultByMedicalReportId(medicalReport.getId());
                    if (labResult != null) {
                        sb.append("Rapor ID: ").append(medicalReport.getId())
                                .append("\nHasta ID: ").append(medicalReport.getPatientId())
                                .append("\nHasta: ").append(info.getName()).append(" ")
                                .append(info.getLastname()).append("\nIcerik: ")
                                .append(medicalReport.getContext()).append("\nLab Sonuc: ")
                                .append(labResult.getContextUrl())
                                .append("\nOlusturulma Tarihi: ").append(medicalReport.getPublicationDate().toString())
                                .append("\n\n");
                    } else {
                        sb.append("Rapor ID: ").append(medicalReport.getId())
                                .append("\nHasta ID: ").append(medicalReport.getPatientId())
                                .append("\nHasta: ").append(info.getName()).append(" ")
                                .append(info.getLastname()).append("\nIcerik: ")
                                .append(medicalReport.getContext())
                                .append("\nOlusturulma Tarihi: ").append(medicalReport.getPublicationDate().toString())
                                .append("\n\n");
                    }
                }
            } catch (SQLException | ClassNotFoundException ex) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(ex.getMessage());
            }
            String infoString = sb.toString();
            request.setAttribute("infoString", infoString);
            request.getRequestDispatcher("/MedicalReport.jsp").forward(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not Allowed !");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!(((String) request.getSession(false).getAttribute("role")).equals("DOCTOR"))) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        if ("DELETE".equalsIgnoreCase(request.getParameter("_method"))) {
            doDelete(request, response);
            return;
        } else if ("PUT".equalsIgnoreCase(request.getParameter("_method"))) {
            doPut(request, response);
            return;
        }
        String patientName = request.getParameter("name");
        String patientLastname = request.getParameter("lastname");
        String context = request.getParameter("context");
        String labResultUrl = request.getParameter("url");
        try {
            PatientManager patientManager = PatientManager.getInstance();
            LoginManager loginManager = LoginManager.getInstance();
            MedicalReportManager medicalReportManager = MedicalReportManager.getInstance();
            Patient patient = patientManager.getPatientByNameAndLastname(patientName, patientLastname);
            HttpSession session = request.getSession(false);
            String username = (String) session.getAttribute("username");
            String pass = (String) session.getAttribute("password");
            Long doctorId = loginManager.getDoctorIdByUsernameAndPassword(username, pass);
            MedicalReportCreatingDao dao = new MedicalReportCreatingDao(patient.getId(), doctorId, context, labResultUrl);
            medicalReportManager.saveMedicalReport(dao);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Successful !");
        } catch (InvalidNameOrLastnameException | SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long reportID = Long.valueOf(request.getParameter("id"));
        String username = (String) request.getSession(false).getAttribute("username");
        String password = (String) request.getSession(false).getAttribute("password");
        try {
            LoginManager loginManager = LoginManager.getInstance();
            MedicalReportManager medicalReportManager = MedicalReportManager.getInstance();
            Long doctorId = loginManager.getDoctorIdByUsernameAndPassword(username, password);
            if (doctorId.longValue() != medicalReportManager.getDoctorIdByReportId(reportID).longValue()) {
                throw new FailureUpdatingException();
            }
            String context = request.getParameter("context");
            String labResultUrl = request.getParameter("labResultUrl");
            MedicalReportUpdatingDao medicalReportUpdatingDao = new MedicalReportUpdatingDao(reportID, context, labResultUrl);
            medicalReportManager.updateMedicalReport(medicalReportUpdatingDao);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Successful !");
        } catch (SQLException | FailureUpdatingException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
        }

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        String pass = (String) session.getAttribute("password");
        Long medicalReportId = Long.valueOf(request.getParameter("id"));
        try {
            LoginManager loginManager = LoginManager.getInstance();
            MedicalReportManager medicalReportManager = MedicalReportManager.getInstance();
            Long doctorId = loginManager.getDoctorIdByUsernameAndPassword(username, pass);
            medicalReportManager.removeMedicalReport(medicalReportId, doctorId);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Successful !");
        } catch (SQLException | FailureDeletingException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
