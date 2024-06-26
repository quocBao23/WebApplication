/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.controller;

import baonhq.cart.CartBean;
import baonhq.order.OrderDAO;
import baonhq.orderDetail.OrderDetailDAO;
import baonhq.product.ProductDAO;
import baonhq.product.ProductDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private final String MARKET_PLACE = "ShowProductServlet";
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
            float total =0;
            
            String url = MARKET_PLACE;
        try {
            //
            HttpSession session = request.getSession(false);
            if (session != null){
                CartBean cart = (CartBean)session.getAttribute("CART");
                if (cart != null){
                    Map<String, Integer> items = cart.getItems();
                    if (items != null){
                        total = cart.totalPayment(cart);
                        // insert into tbl.Orders
                        OrderDAO orderdao = new OrderDAO();
                        boolean resultOrder = orderdao.insertCustomerInform(customerName, customerAddress, customerEmail, total);
//                        if (resultOrder){
//                          session.removeAttribute("CART");
//                        }
//                        // insert into tbl.OrderDetail
                       
                        for (String key : items.keySet()) {
                            // get information
                            ProductDAO productdao = new ProductDAO();
                            ProductDTO dto = null;
                            dto = productdao.getProduct(key);
                            
                            int productID = dto.getSku();
                            float unitPrice = dto.getPrice();
                            int quantity = items.get(key);
                            String orderID = orderdao.getOrderID();
                            float totalProduct = unitPrice * quantity;
                            OrderDetailDAO oddao = new OrderDetailDAO();
                            boolean result = oddao.insertOrderDetail(productID, unitPrice, quantity, orderID, totalProduct); 
                            
                        }
                        session.removeAttribute("CART");
                        
                        
                        
                        
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (NamingException ex) {
            ex.printStackTrace();
        }finally{
            response.sendRedirect(url);
            
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
