/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package patientmanagementsystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import patientmanagementsystem.dao.DoctorInfoDao;
import patientmanagementsystem.dao.ElementIdDao;
import patientmanagementsystem.model.MedicalReport;
import patientmanagementsystem.service.DoctorManager;
import patientmanagementsystem.service.LoginManager;
import patientmanagementsystem.service.MedicalReportManager;

/**
 *
 * @author kaan
 */
@WebServlet(name = "PatientMedicalReportController", urlPatterns = {"/patient-report"})
public class PatientMedicalReportController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession(false).getAttribute("role").equals("PATIENT")) {
            String username = (String) request.getSession().getAttribute("username");
            String password = (String) request.getSession().getAttribute("password");
            try {
                Long patientId = LoginManager.getInstance().getPatientIdByUsernameAndPassword(username, password);
                List<MedicalReport> medicalReports = MedicalReportManager.getInstance().getAllMedicalReportsByPatientId(patientId);
                StringBuilder sb = new StringBuilder();
                for (MedicalReport medicalReport : medicalReports) {
                    ElementIdDao idDao = new ElementIdDao(medicalReport.getDoctorId());
                    DoctorInfoDao doctorInfo = DoctorManager.getInstance().showDoctor(idDao);
                    sb.append("Yazar : ").append(doctorInfo.getName())
                            .append(" ").append(doctorInfo.getLastname())
                            .append("\n").append("Icerik : ")
                            .append(medicalReport.getContext()).append("\n")
                            .append("Rapor Yazilma Tarihi : ")
                            .append(medicalReport.getPublicationDate().toString())
                            .append("\n\n\n");
                }
                String fullContext = sb.toString();
                request.setAttribute("context", fullContext);
                request.getRequestDispatcher("/PatientMedicalReportPanel.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException ex) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(ex.getMessage());
            }
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not Allowed");
        }
    }


    @Override
    public String getServletInfo() {
        return "A controller for directing the patient to its medical reports";
    }// </editor-fold>

}
