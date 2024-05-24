/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package patientmanagementsystem.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import patientmanagementsystem.dao.DoctorInfoDao;
import patientmanagementsystem.dao.ElementIdDao;
import patientmanagementsystem.dao.MedicalReportCreatingDao;
import patientmanagementsystem.dao.MedicalReportUpdatingDao;
import patientmanagementsystem.dao.PatientInfoDao;
import patientmanagementsystem.exception.FailureDeletingException;
import patientmanagementsystem.exception.InvalidNameOrLastnameException;
import patientmanagementsystem.model.MedicalReport;
import patientmanagementsystem.service.DoctorManager;
import patientmanagementsystem.service.MedicalReportManager;
import patientmanagementsystem.service.PatientManager;

/**
 *
 * @author kaan
 */
@WebServlet(name = "AdminMedicalReportController", urlPatterns = {"/admin-report"})
public class AdminMedicalReportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String role = (String) request.getSession(false).getAttribute("role");
        if (!role.equals("ADMIN")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not Allowed.");
            return;
        }
        try {
            List<MedicalReport> reports = MedicalReportManager.getInstance().getAllMedicalReports();
            StringBuilder sb = new StringBuilder();
            for (MedicalReport report : reports) {
                DoctorInfoDao doctorInfo = DoctorManager.getInstance().showDoctor(new ElementIdDao(report.getDoctorId()));
                PatientInfoDao patientInfo = PatientManager.getInstance().showPatient(new ElementIdDao(report.getPatientId()));
                sb.append("Rapor ID: ").append(report.getId())
                        .append("\nDoktor Id: ").append(report.getDoctorId())
                        .append("\nHasta ID: ").append(report.getPatientId())
                        .append("\nDoktor:").append(doctorInfo.getName())
                        .append(" ").append(doctorInfo.getLastname())
                        .append("\nHasta: ").append(patientInfo.getName())
                        .append(" ").append(patientInfo.getLastname())
                        .append("\nIcerik: ").append(report.getContext())
                        .append("\nYazilma Tarihi: ").append(report.getPublicationDate().toString());
            }
            String infoString = sb.toString();
            request.setAttribute("infoString", infoString);
            request.getRequestDispatcher("/AdminMedicalReportPanel.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!(((String) request.getSession(false).getAttribute("role")).equals("ADMIN"))) {
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
        Long patientId = Long.valueOf(request.getParameter("patientId"));
        Long doctorId = Long.valueOf(request.getParameter("doctorId"));
        String context = request.getParameter("context");
        String labResultUrl = request.getParameter("url");
        try {
            MedicalReportManager medicalReportManager = MedicalReportManager.getInstance();
            MedicalReportCreatingDao dao = new MedicalReportCreatingDao(patientId, doctorId, context, labResultUrl);
            medicalReportManager.saveMedicalReport(dao);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Successful !");
        } catch (InvalidNameOrLastnameException | SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long patientId = Long.valueOf(request.getParameter("patientId"));
        Long doctorId = Long.valueOf(request.getParameter("doctorId"));
        try {
            MedicalReportManager medicalReportManager = MedicalReportManager.getInstance();
            List<MedicalReport> reports = medicalReportManager.getAllMedicalReportsByPatientId(patientId);
            for (MedicalReport medicalReport : reports) {
                if (medicalReport.getDoctorId().longValue() == doctorId.longValue()) {
                    medicalReportManager.removeMedicalReport(medicalReport.getId(), doctorId);
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write("Successful !");
                    return;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(ex.getMessage());
        } catch (FailureDeletingException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long reportId = Long.valueOf(request.getParameter("reportId"));
        Long patientId = Long.valueOf(request.getParameter("patientId"));
        Long doctorId = Long.valueOf(request.getParameter("doctorId"));
        String context = request.getParameter("context");
        String url = request.getParameter("url");
        try {
            MedicalReportManager.getInstance().updateMedicalReportByAdmin(reportId, doctorId, patientId, context, url);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Successful !");
        } catch (SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
