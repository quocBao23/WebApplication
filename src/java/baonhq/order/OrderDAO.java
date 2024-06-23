/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.order;

import baonhq.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class OrderDAO implements Serializable {
    private int count;
    private String orderID = null;

    public String getOrderID() {
        return orderID;
    }
    

    public OrderDAO() throws SQLException, NamingException {
        this.count = checkOrderRow();
    }
    public int checkOrderRow() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        int count = 0;
        try {
        //1.Connect DB          b11 connect
            con = DBHelper.getConnection();
        //2. Create SQL String
            String sql = "select count(orderID) as count "
                    +    "from Orders";
                    
        //3. Create Statement Obj
            stm = con.prepareStatement(sql);
        //4. Execute querry
            rs = stm.executeQuery();
        //5. Process result     b13 mapped
            if ( rs.next() ){
                count = rs.getInt("count");
            } // end username and password are verifited
            
        } finally {
            if(rs!= null){
                rs.close();
            }if ( stm != null){
                stm.close();;
            }
            if (con != null){
                con.close();
            }
        }
        
         return count;  
    }
    
    public boolean insertCustomerInform(String customerName, String customerAddress, String customerEmail, float total) throws SQLException, NamingException{
        
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        
        
        if (count >=0 && count < 10 ){
            orderID = "ODOO".concat(String.valueOf(count +1));
        }else if (count >= 10 && count < 100 ){
            orderID = "ODO".concat(String.valueOf(count +1));
        }else {
            orderID = "OD".concat(String.valueOf(count +1));
        }
        Date date = new Date(System.currentTimeMillis());
        
        try {
        //1.Connect DB          b11 connect
            con = DBHelper.getConnection();
        //2. Create SQL String
            String sql = "insert into Orders "
                    + "values(?, ?, ?, ?, ?, ?) ";
                    
        //3. Create Statement Obj
            stm = con.prepareStatement(sql);
            stm.setString(1, orderID);
            stm.setDate(2, date);
            stm.setString(3, customerName);
            stm.setString(4, customerAddress);
            stm.setString(5, customerEmail);
            stm.setFloat(6, total);
        //4. Execute querry
            int i = stm.executeUpdate();
        //5. Process result     b13 mapped
            if ( i > 0 ){
                result = true; 
            } // end 
            
        } finally {
            if ( stm != null){
                stm.close();;
            }
            if (con != null){
                con.close();
            }
        }
        
        return result;
        
    }
    
   
    
}
