/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author ADMIN
 */
public class DBHelper implements Serializable{
    // liên kết tĩnh
    public static Connection makeConnection() 
            throws /*ClassNotFoundException,*/ SQLException, NamingException {

        // 1. get current context
        Context context = new InitialContext();
        // 2. get tomcat context
        Context tomcat = (Context) context.lookup("java:comp/env");
        // 3. lookup data Source
        DataSource ds = (DataSource) tomcat.lookup("BeanDS");
        // 4. Connection
        Connection conn = ds.getConnection();
        
        return conn;
//        // 1. Load Driver
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        // 2. Create Connection Stirng
//        String url = "jdbc:sqlserver://localhost:1433;"
//                + "databaseName=PRJ301ThayKhanhKT;instanceName=NHANNHAN";
//        // 3. Open Connection
//        Connection conn = DriverManager.getConnection(url, "sa", "12345");
//        
//        return conn;
    }
}
