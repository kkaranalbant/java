/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package patientmanagementsystem.controller;

import java.io.IOException;
import java.time.LocalDate;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import patientmanagementsystem.dao.ElementIdDao;
import patientmanagementsystem.dao.PatientRegisterDao;
import patientmanagementsystem.dao.PatientUpdatingDao;
import patientmanagementsystem.exception.FailureDeletingException;
import patientmanagementsystem.exception.FailureUpdatingException;
import patientmanagementsystem.exception.UnsavedPersonException;
import patientmanagementsystem.model.Gender;
import patientmanagementsystem.service.PatientManager;

/**
 *
 * @author kaan
 */
@WebServlet(name = "AdminPatientController", urlPatterns = {"/patient"})
public class AdminPatientController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!((String) request.getSession(false).getAttribute("role")).equals("ADMIN")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not allowed.");
            return;
        }
        request.getRequestDispatcher("/Patient.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!((String) request.getSession(false).getAttribute("role")).equals("ADMIN")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not allowed.");
            return;
        }
        if (request.getParameter("_method") == null) {
            String name = request.getParameter("name");
            String lastname = request.getParameter("lastname");
            LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
            Gender gender = Gender.valueOf(request.getParameter("gender"));
            String address = request.getParameter("address");
            String phoneNumber = request.getParameter("phoneNumber");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            PatientRegisterDao patientRegisterDao = new PatientRegisterDao(name, lastname, birthDate, gender, address, phoneNumber, username, password);

            try {
                PatientManager patientManager = PatientManager.getInstance();
                patientManager.savePatient(patientRegisterDao);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Successful !");
            } catch (SQLException | ClassNotFoundException ex) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(ex.getMessage());
            }
        } else if (("DELETE").equalsIgnoreCase(request.getParameter("_method"))) {
            doDelete(request, response);
        } else if (("PUT").equalsIgnoreCase(request.getParameter("_method"))) {
            doPut(request, response);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!((String) request.getSession(false).getAttribute("role")).equals("ADMIN")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not allowed.");
            return;
        }
        Long id = Long.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        Gender gender = Gender.valueOf(request.getParameter("gender"));
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PatientUpdatingDao patientUpdatingDao = new PatientUpdatingDao(id, name, lastname, birthDate, gender, address, phoneNumber, username, password);

        try {
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
        if (!((String) request.getSession(false).getAttribute("role")).equals("ADMIN")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not allowed.");
            return;
        }
        Long id = Long.valueOf(request.getParameter("id"));
        ElementIdDao personIdDao = new ElementIdDao(id);
        try {
            PatientManager patientManager = PatientManager.getInstance();
            patientManager.removePatient(personIdDao);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Successful !");
        } catch (FailureDeletingException | SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
        }

    }

    @Override
    public String getServletInfo() {
        return "Patient Controller";
    }// </editor-fold>

}
