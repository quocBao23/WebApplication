/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.controller;

import java.io.IOException;
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
@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
public class DispatchServlet extends HttpServlet {
    private final String LOGIN_PAGE = "login.html";
    private final String VIEW_CART_PAGE = "viewcart.jsp";
    
    private final String LOGIN_CONTROLLER ="LoginServlet";
    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastNameServlet";
    private final String DELETE_ACCOUNT_CONTROLLER =  "DeleteAccountServlet";
    private final String UPDATE_CONTROLLER = "UpdateServlet";
    private final String STARTUP_CONTROLLER = "StartUpServlet";
    private final String ADD_TO_CART_CONTROLLER = "AddToCartServlet";
    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "RemoveItemServlet";
    private final String CHECK_OUT_CONTROLLER = "CheckOutServlet";
    private final String CREATE_ACCOUNT_CONTROLLER ="CreateAccountServlet";
    private final String LOG_OUT_CONTROLLER ="LogOutServlet";
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
        
            // which button did user click 
            String button = request.getParameter("btAction");
            String url = LOGIN_PAGE;
            
        try {
            if (button == null){
                //check cookies
                url = STARTUP_CONTROLLER;
            }else if (button.equals("Login") ){
                url = LOGIN_CONTROLLER;
            }else if (button.equals("Search")){
                url = SEARCH_LASTNAME_CONTROLLER;
            }else if (button.equals("Delete")){
                url = DELETE_ACCOUNT_CONTROLLER;
            }else if  (button.equals("Update")){
                url =  UPDATE_CONTROLLER;
            }else if ( button.equals("Add Book to your Cart")){
                url = ADD_TO_CART_CONTROLLER;
            }else if ( button.equals("View Cart")){
                url = VIEW_CART_PAGE;
            }else if (button.equals("Remove Selected Items")){
                url = REMOVE_ITEM_FROM_CART_CONTROLLER;
            }else if (button.equals("Check Out")){
                url = CHECK_OUT_CONTROLLER;
            }else if (button.equals("Create Account")){
                url = CREATE_ACCOUNT_CONTROLLER;
            }else if (button.equals("Log Out")){
                url = LOG_OUT_CONTROLLER;
            }
            
            
            
            
        }finally{
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
