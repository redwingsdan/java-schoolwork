package app;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author djp217
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/submit"})
public class RegisterServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        // No GET behavior.
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        RegisterBean rb = new RegisterBean();
        rb.setFirstName(request.getParameter("first-name"));
        rb.setLastName(request.getParameter("last-name"));
        rb.setEmail(request.getParameter("email"));
        rb.setPassword(request.getParameter("password"));
        rb.setUsername(request.getParameter("username"));
        rb.setCountry(request.getParameter("country"));
        rb.setCity(request.getParameter("city"));
        rb.setLanguage(request.getParameter("language"));
        rb.setSecurityQuestion(request.getParameter("security-question"));
        rb.setSecurityAnswer(request.getParameter("security-answer"));
        request.setAttribute("rb", rb);
        
        if(!rb.isValid())
        {
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
        else
        {
            request.getRequestDispatcher("debug.jsp").forward(request, response);
        }
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
