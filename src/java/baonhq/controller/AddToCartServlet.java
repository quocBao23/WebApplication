/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.controller;

import baonhq.cart.CartBean;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {
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
        
        String url = MARKET_PLACE;
        try  {
            //1. Customer goes to the cart place
            HttpSession session = request.getSession();
            //2. Customer takes cart
            // Giỏ hàng đặt ở nơi chứa giỏ, ở đây là đặt ở Session Scope
            // Nếu giỏ hàng chưa có thì new mới
            CartBean cart = (CartBean)session.getAttribute("CART");
            if (cart == null){
                cart = new CartBean();
            }
            //3. Cus drops an item to cart
            String item = request.getParameter("dtlBook");
            int quantity = Integer.parseInt(request.getParameter("txtQuantity"));
            cart.addItemToCart(item,quantity);
            // món đồ đang trong tay customer, local, vì vậy phải set lại Attribute
            //update lại giỏ hàng sau khi bỏ hàng
            session.setAttribute("CART", cart);
            //4. Customer goes shopping
            // chọn lại đồ, thực chất là quay lại trang market
            
        } finally{
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
