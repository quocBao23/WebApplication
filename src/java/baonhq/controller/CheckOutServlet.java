/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.controller;

import baonhq.cart.CartBean;
import baonhq.order.OrderDAO;
import baonhq.order.OrderDTO;
import baonhq.order.OrderInformError;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {

    //private final String MARKET_PLACE = "ShowProductServlet";
    private final String CHECK_OUT_ERROR = "viewcarterror.jsp";
    private final String CHECK_OUT_ITEM = "CheckOutItemServlet";

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
        // 1. get all information
        String customerName = request.getParameter("customerName");
        String customerAddress = request.getParameter("customerAddress");
        String customerEmail = request.getParameter("customerEmail");
        
        float totalPayment = 0;
        
         Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        
        // Create a Error Obj
        OrderInformError error = new OrderInformError();
        boolean found = false;
        String url = CHECK_OUT_ERROR;

        try {
            //Customer goes to the cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //Customer takes cart
                CartBean cart = (CartBean) session.getAttribute("CART");
                if (cart != null) {
                    //Customer takes items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        
                        // Catch user error
                        if (customerName.trim().isEmpty()) {
                            found = true;
                            error.setUserNameError("Name cannot be blank");
                        }
                        if (customerAddress.trim().isEmpty()) {
                            found = true;
                            error.setUserAddressError("Address cannot be blank");
                        }
                        if (!customerEmail.trim().isEmpty() && !customerEmail.contains("@gmail.com")) {
                            found = true;
                            error.setUserEmailFormatError("Email should be include @gmail.com");
                        }
                        if (found) {
                            // Set error vao 1 attribue
                            request.setAttribute("USER_INFORM_ERROR", error);
                        } else {   // Insert 
                            //2.1 New DAO object
                            OrderDAO orderdao = new OrderDAO();
                            int count = orderdao.checkOrderRow();
                            String orderID = String.format("OD%03d", ++count);
                            totalPayment = cart.getTotal();
                            OrderDTO dto = new OrderDTO(orderID, currentTime, customerName, customerAddress, customerEmail, totalPayment);
                            request.setAttribute("ORDER", dto);
                            //2.2 Call method
                            boolean result = orderdao.insertCustomerInform(dto);
                            if (result) {
                                url = CHECK_OUT_ITEM;
                            }
                        }

                    }
                }
            }
        } catch (SQLException ex) {
            log("CheckOutServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckOutServlet_Naming " + ex.getMessage());
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
