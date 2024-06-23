/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baonhq.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author Admin
 */
public class DBHelper {
    public static Connection  getConnection() throws /*ClassNotFoundException,*/  SQLException, NamingException {
        //1. get curren context
        Context context = new InitialContext();
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        //2.lookup ds
        DataSource ds = (DataSource)tomcatContext.lookup("DV007");
        //3. open connection
        Connection con = ds.getConnection();
        //4.return con
        return con;
        
        
//        //1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        
//        //2. Create Connection String: protocol://ip:port;databaseName=
//        String url = "jdbc:sqlserver://"    //protocol
//                + "localhost:1433;"         //ip:port
//                + "databaseName=JDBC" ;
//        //3. getConection from DriverManager
//        Connection con = DriverManager.getConnection(url, "sa", "123456");
//        
//        return con;
    }
}
