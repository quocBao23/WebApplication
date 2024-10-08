/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.controller;

import baonhq.cart.CartBean;
import baonhq.order.OrderDTO;
import baonhq.orderDetail.OrderDetailDAO;
import baonhq.orderDetail.OrderDetailDTO;
import baonhq.product.ProductDAO;
import baonhq.product.ProductDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
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
@WebServlet(name = "CheckOutItemServlet", urlPatterns = {"/CheckOutItemServlet"})
public class CheckOutItemServlet extends HttpServlet {
    private final String SHOW_RECEIPT = "receipt.jsp";
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
        
        String url = SHOW_RECEIPT ;
        Map<String, OrderDetailDTO> receipt = null;
        
        try  {
            HttpSession session = request.getSession(false);
            if (session != null) {
                CartBean cart = (CartBean) session.getAttribute("CART");
                if (cart != null) {
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        
                        OrderDTO orderDTO = (OrderDTO)request.getAttribute("ORDER");
                        // insert into tbl.OrderDetail
                        for (String key : items.keySet()) {
                            // get information
                            ProductDAO productDAO = new ProductDAO();
                            ProductDTO productDTO = null;
                            productDTO = productDAO.getProduct(key); 
                            
                            //New DAO obj
                            OrderDetailDAO dao = new OrderDetailDAO();
                            int id = dao.checkOrderDetailRow();
                            int productID = productDTO.getSku();
                            float unitPrice = productDTO.getPrice();
                            int quantity = items.get(key);
                            String orderID = orderDTO.getOrderID();
                            float totalProductPayment = unitPrice * quantity;
                            
                            OrderDetailDTO dto = new OrderDetailDTO(id, productID, unitPrice, quantity, orderID, unitPrice); 
                            // Call method , insert to DB
                            boolean result = dao.insertOrderDetail(dto); 
                            if ( receipt == null ){
                                receipt = new HashMap<>();
                            }
                            if (result){
                                receipt.put(key, dto);
                            }
                            
                                                                             
                        }
                        request.setAttribute("ORDER_DETAIL", receipt);   
                        session.removeAttribute("CART");
                    }
                }
            }
        } catch (SQLException ex) {            
            log("CheckOutItemServlet_SQL " + ex.getMessage());
        } catch (NamingException ex) {
            log("CheckOutItemServlet_Naming " + ex.getMessage() );
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
