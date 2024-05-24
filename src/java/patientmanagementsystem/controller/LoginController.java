/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package patientmanagementsystem.controller;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import patientmanagementsystem.dao.LoginDao;
import patientmanagementsystem.exception.FailureLoginException;
import patientmanagementsystem.service.LoginManager;

/**
 *
 * @author kaan
 */
@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession(true);
        String url = "/login.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginDao loginDao = new LoginDao(username, password);
        String url = null;
        try {
            LoginManager.getInstance().authenticateUser(request, loginDao);
            String role = (String) request.getSession(false).getAttribute("role");
            url = null ;
            if (role.equals("ADMIN")) {
                url = "/AdminMainPanel.jsp";
            }
            else if (role.equals("DOCTOR")) {
                url = "/DoctorMainPanel.jsp";
            }
            else {
                url = "/PatientMainPanel.jsp";
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);

        } catch (FailureLoginException | ClassNotFoundException | SQLException e) {
            url = "/login.jsp";
            request.setAttribute("errorMessage", e.getMessage());
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
