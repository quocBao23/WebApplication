/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.orderDetail;

import baonhq.util.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class OrderDetailDAO implements Serializable{
    private int count ;

    public int getCount() {
        return count;
    }
    
    public OrderDetailDAO() throws SQLException, NamingException {
        this.count = checkOrderDetailRow();
    }
    
    
       public int checkOrderDetailRow() throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int count = 0;
        try {
        //1.Connect DB          b11 connect
            con = DBHelper.getConnection();
        //2. Create SQL String
            String sql = "select count(id) as row "
                    +    "from OrderDetail";
                    
        //3. Create Statement Obj
            stm = con.prepareStatement(sql);
        //4. Execute querry
            rs = stm.executeQuery();
        //5. Process result     b13 mapped
            if ( rs.next() ){
                count = rs.getInt("row");
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
    
    public boolean insertOrderDetail(int productID, float unitPrice, int quantity, String orderID, float total) throws SQLException, NamingException{
        
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        int count = 0;
        
//        if (count >0 && count < 10 ){
//            orderID = "ODOO".concat(String.valueOf(count +1));
//        }else if (count >= 10 && count < 100 ){
//            orderID = "ODO".concat(String.valueOf(count +1));
//        }else {
//            orderID = "OD".concat(String.valueOf(count +1));
//        }
        Date date = new Date(System.currentTimeMillis());
        
        try {
        //1.Connect DB          b11 connect
            con = DBHelper.getConnection();
        //2. Create SQL String
            String sql = "insert into OrderDetail "
                    + "values(?, ?, ?, ?, ?, ?) ";
                    
        //3. Create Statement Obj
            stm = con.prepareStatement(sql);
            stm.setInt(1, this.count + 1 );
            stm.setInt(2, productID);
            stm.setFloat(3, unitPrice);
            stm.setInt(4, quantity);
            stm.setString(5, orderID);
            stm.setFloat(6, total);
        //4. Execute querry
            int i = stm.executeUpdate();
        //5. Process result     b13 mapped
            if ( i > 0 ){
                result = true; 
            } // end username and password are verifited
            
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
