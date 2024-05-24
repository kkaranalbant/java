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
import java.time.LocalDate;
import patientmanagementsystem.dao.DoctorRegisterDao;
import patientmanagementsystem.dao.DoctorUpdatingDao;
import patientmanagementsystem.dao.ElementIdDao;
import patientmanagementsystem.exception.FailureDeletingException;
import patientmanagementsystem.exception.FailureUpdatingException;
import patientmanagementsystem.exception.UnsavedPersonException;
import patientmanagementsystem.model.Gender;
import patientmanagementsystem.service.DoctorManager;

/**
 *
 * @author kaan
 */
@WebServlet(name = "AdminDoctorController", urlPatterns = {"/doctor"})
public class AdminDoctorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (((String) request.getSession(false).getAttribute("role")).equals("ADMIN")) {
            request.getRequestDispatcher("/Doctor.jsp").forward(request, response);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not Allowed !");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!((String) request.getSession(false).getAttribute("role")).equals("ADMIN")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not allowed.");
            return;
        }
        if (("DELETE").equalsIgnoreCase(request.getParameter("_method"))) {
            doDelete(request, response);
            return;
        }
        if (("PUT").equalsIgnoreCase(request.getParameter("_method"))) {
            doPut(request, response);
            return;
        }
        String name = request.getParameter("name");
        String lastname = request.getParameter("lastname");
        LocalDate birthDate = LocalDate.parse(request.getParameter("birthDate"));
        Gender gender = Gender.valueOf(request.getParameter("gender"));
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String branch = request.getParameter("branch");
        String workingPlace = request.getParameter("workingPlace");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        DoctorRegisterDao doctorRegisterDao = new DoctorRegisterDao(name, lastname, birthDate, gender, address, phoneNumber, branch, workingPlace, username, password);
        try {
            DoctorManager doctorManager = DoctorManager.getInstance();
            doctorManager.saveDoctor(doctorRegisterDao);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Successful !");
        } catch (UnsavedPersonException | SQLException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
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
        String branch = request.getParameter("branch");
        String workingPlace = request.getParameter("workingPlace");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        DoctorUpdatingDao doctorUpdatingDao = new DoctorUpdatingDao(id, name, lastname, birthDate, gender, address, phoneNumber, branch, workingPlace, username, password);
        try {
            DoctorManager doctorManager = DoctorManager.getInstance();
            doctorManager.updateDoctor(doctorUpdatingDao);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Successful !");
        } catch (SQLException | FailureUpdatingException | ClassNotFoundException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!((String) request.getSession(false).getAttribute("role")).equals("ADMIN")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Not allowed.");
            return;
        }
        Long id = Long.valueOf(request.getParameter("id"));
        ElementIdDao doctorIdDao = new ElementIdDao(id);
        try {
            DoctorManager doctorManager = DoctorManager.getInstance();
            doctorManager.removeDoctor(doctorIdDao);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Successful !");
        } catch (FailureDeletingException | SQLException | ClassNotFoundException ex) {
            response.getWriter().write("Error: " + ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Doctor Controller";
    }// </editor-fold>

}
