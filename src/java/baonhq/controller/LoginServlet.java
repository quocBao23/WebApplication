/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.controller;

import baonhq.registration.RegistrationDAO;
import baonhq.registration.RegistrationDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

    // khai bao bien hang
//    private final String SEARCH_PAGE = "search.html";
    private final String SEARCH_PAGE = "search.jsp";
    private final String ERROR_PAGE = "error.html";

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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = ERROR_PAGE;

        try {

            //1. get all user information
            String name = request.getParameter("txtUsername");
            String pass = request.getParameter("txtPassword");

            //2. Call method of Model / DAO
            //2.1 New DAO object
            RegistrationDAO dao = new RegistrationDAO();
            //2.2 Call method of DAO object
//            boolean result = dao.checkLogin(name, pass);
            RegistrationDTO result = dao.checkLogin(name, pass);
            //3. process result 
            if (result != null) {
                url = SEARCH_PAGE;
                HttpSession session = request.getSession();
                session.setAttribute("USER", result);
                session.setAttribute("USERNAME", name);
                // if login sucess => server send and client store cookies 
//                 Cookie cookie = new Cookie(name, pass);
//                 cookie.setMaxAge(60*30);
//                 response.addCookie(cookie); 
            }
            // end user click 

        } catch (NamingException ex) {            
            log("LoginServlet_Naming " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginServlet_SQL " + ex.getMessage());
        } finally {
//            response.sendRedirect(url);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
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
        processRequest(request, response);
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
