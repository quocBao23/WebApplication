/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.product;

import baonhq.registration.RegistrationDTO;
import baonhq.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class ProductDAO implements Serializable{
    private List<ProductDTO> productList ;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    
    public List<ProductDTO> showListCart() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        
        try {
        //1.Connect DB          b11 connect
            con = DBHelper.getConnection();
        //2. Create SQL String
            String sql = "select sku, name, description, quantity, price, status "
                    + "from Product "
                    + "where quantity > ? and status = ? ";
            
        //3. Create Statement Obj
            stm = con.prepareStatement(sql);
            stm.setInt(1, 0);
            stm.setBoolean(2, true);
            
        //4. Execute querry
            rs = stm.executeQuery();
            
        //5. Process result     b13 mapped
            while ( rs.next() ){
              //map
              //get data from result set
              int sku = rs.getInt("sku");
              String name = rs.getString("name");
              String description = rs.getString("description");
              int quantity = rs.getInt("quantity");
              float price = rs.getFloat("price");     
              boolean status = rs.getBoolean("status");
              //set data to DTO properties
              ProductDTO dto = new ProductDTO(sku, name, description, quantity, price, status);
              if (this.productList == null){
                  this.productList = new ArrayList<>();
              }
              this.productList.add(dto);
              
            } // 
            
        } finally {
            if (rs != null){
                rs.close();
            }
            if ( stm != null){
                stm.close();;
            }
            if (con != null){
                con.close();
            }
        }
        return productList;
    }
    
    public ProductDTO getProduct(String itemName) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        ProductDTO dto = null;
        try {
        //1.Connect DB          b11 connect
            con = DBHelper.getConnection();
            
        //2. Create SQL String
            String sql = "select sku, name, description, quantity, price, status "
                    + "from Product "
                    + "where name = ? ";
            
        //3. Create Statement Obj
            stm = con.prepareStatement(sql);
            stm.setString(1, itemName);
            
        //4. Execute querry
            rs = stm.executeQuery();
            
        //5. Process result     b13 mapped
            if ( rs.next() ){
              //map
              //get data from result set
              int sku = rs.getInt("sku");
              String name = rs.getString("name");
              String description = rs.getString("description");
              int quantity = rs.getInt("quantity");
              float price = rs.getFloat("price");     
              boolean status = rs.getBoolean("status");
              //set data to DTO properties
               dto = new ProductDTO(sku, name, description, quantity, price, status);
            } // 
            
        } finally {
            if (rs != null){
                rs.close();
            }
            if ( stm != null){
                stm.close();;
            }
            if (con != null){
                con.close();
            }
        }
        return dto;
        
    }
}
