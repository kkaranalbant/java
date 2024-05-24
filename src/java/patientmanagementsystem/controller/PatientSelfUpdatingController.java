/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package patientmanagementsystem.controller;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import patientmanagementsystem.dao.ElementIdDao;
import patientmanagementsystem.dao.PatientInfoDao;
import patientmanagementsystem.dao.PatientUpdatingDao;
import patientmanagementsystem.exception.FailureUpdatingException;
import patientmanagementsystem.model.Gender;
import patientmanagementsystem.service.LoginManager;
import patientmanagementsystem.service.PatientManager;

/**
 *
 * @author kaan
 */
@WebServlet(name = "PatientSelfUpdatingController", urlPatterns = {"/patient-self-update"})
public class PatientSelfUpdatingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (((String) (request.getSession(false).getAttribute("role"))).equals("PATIENT")) {
            String username = (String) request.getSession(false).getAttribute("username");
            String password = (String) request.getSession(false).getAttribute("password");
            try {
                Long id = LoginManager.getInstance().getPatientIdByUsernameAndPassword(username, password);
                PatientInfoDao info = PatientManager.getInstance().showPatient(new ElementIdDao(id));
                request.setAttribute("name", info.getName());
                request.setAttribute("lastname", info.getLastname());
                request.setAttribute("gender", info.getGender().name());
                request.setAttribute("phoneNumber", info.getPhoneNumber());
                request.setAttribute("address", info.getAddress());
                request.setAttribute("birthDate", info.getBirthDate());
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                StringBuilder sb = new StringBuilder();
                sb.append("ID: ").append(info.getId()).append("\nAd: ")
                        .append(info.getName()).append(" \nSoyad: ")
                        .append(info.getLastname()).append("\nCinsiyet: ")
                        .append(info.getGender().name()).append("\nDogum Tarihi: ")
                        .append(info.getBirthDate().toString()).append("\nTelefon No: ")
                        .append(info.getPhoneNumber()).append("\nAdres: ")
                        .append(info.getAddress()).toString();
                String infoString = sb.toString();
                request.setAttribute("infoString", infoString);
                request.getRequestDispatcher("/PatientSelfProcessesPanel.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException ex) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(ex.getMessage());
            }
        } else {
            response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not Allowed");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession(false).getAttribute("role").equals("ADMIN") || request.getSession(false).getAttribute("role").equals("DOCTOR")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not allowed !");
        }
        if (("DELETE").equalsIgnoreCase(request.getParameter("_method"))) {
            doDelete(request, response);
        }
        if (("PUT").equalsIgnoreCase(request.getParameter("_method"))) {
            doPut(request, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession(false).getAttribute("role").equals("ADMIN") || request.getSession(false).getAttribute("role").equals("DOCTOR")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not allowed !");
        }
        String username = (String) request.getSession(false).getAttribute("username");
        String password = (String) request.getSession(false).getAttribute("password");
        try {
            Long id = LoginManager.getInstance().getPatientIdByUsernameAndPassword(username, password);
            String name = request.getParameter("name");
            String lastname = request.getParameter("lastname");
            LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
            Gender gender = Gender.valueOf(request.getParameter("gender"));
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            String usernameNew = request.getParameter("username");
            String passwordNew = request.getParameter("password");
            PatientUpdatingDao patientUpdatingDao = new PatientUpdatingDao(id, name, lastname, birthDate, gender, address, phoneNumber, usernameNew, passwordNew);
            PatientManager patientManager = PatientManager.getInstance();
            patientManager.updatePatient(patientUpdatingDao);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Successful !");
        } catch (SQLException | FailureUpdatingException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (((String) (request.getSession(false).getAttribute("role"))).equals("PATIENT")) {
            String username = (String) request.getSession(false).getAttribute("username");
            String password = (String) request.getSession(false).getAttribute("password");
            try {
                Long id = LoginManager.getInstance().getPatientIdByUsernameAndPassword(username, password);
                PatientManager.getInstance().removePatient(new ElementIdDao(id));
                LoginManager.getInstance().removeRoleFromHeader(request);
                response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_OK);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException ex) {
                response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN);
                response.getWriter().write("Not Allowed");
            }

        } else {
            response.setStatus(jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not Allowed");
        }
    }

    @Override
    public String getServletInfo() {
        return "Patient Self Updating Controller";
    }// </editor-fold>

}
