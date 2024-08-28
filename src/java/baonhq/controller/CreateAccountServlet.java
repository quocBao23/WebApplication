/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.controller;

import baonhq.registration.RegistrationCreateError;
import baonhq.registration.RegistrationDAO;
import baonhq.registration.RegistrationDTO;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {

    private final String ERROR_PAGE = "createAccount.jsp";
    private final String LOGIN_PAGE = "login.html";

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
        //1. get all information
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String confirm = request.getParameter("txtConfirm");
        String fullName = request.getParameter("txtFullname");
        boolean foundErr = false;
        
        RegistrationCreateError errors = new RegistrationCreateError();
        String url = ERROR_PAGE;
        try {
            //2.check all user error
            if (username.trim().length() < 5 || username.trim().length() > 30) {
                foundErr = true;
                errors.setUserNameLength("username is required from 5 to 30");
            }
            if (password.trim().length() < 6 || password.trim().length() > 30) {
                foundErr = true;
                errors.setUserPasswordLength("password is required from 6 to 30");
            } else if (!confirm.trim().equals(password.trim())) {
                foundErr = true;
                errors.setConfirmNotMatched("Not Matched");
            }
            if (fullName.trim().length() < 2 || fullName.trim().length() > 50) {
                foundErr = true;
                errors.setUserFullNameLength("FullName is required from 2 to 50");
            }
            if (foundErr) {
                // catching Error vao 1 attribute
                request.setAttribute("FOUND_ERROR", errors);
            } else {
                //3. call method
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO dto = new RegistrationDTO(username, password, fullName, false);
                boolean result = dao.createAccount(dto);
                if (result){
                    //4.process
                    url = LOGIN_PAGE;
                }
                
            }

        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("AddAccountServlet_SQL " + ex.getMessage());
            if ( msg.contains("duplicate")){
                errors.setUserNameIsExisted(username + "is exited");
                request.setAttribute("FOUND_ERROR", errors);
            }
        } catch (NamingException ex) {
            log("AddAccountServlet_Naming " + ex.getMessage() );
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

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
