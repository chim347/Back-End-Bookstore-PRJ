/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nhan.util.DBHelper;

/**
 *
 * @author ADMIN
 */
public class RegistrationDAO implements Serializable {

    public /*boolean*/ RegistrationDTO checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //boolean flag = false;
        RegistrationDTO dto = null;
        try {
            // 1. connect DB
            conn = DBHelper.makeConnection();
            if (conn != null) {
                // 2. write sql command
                String sql = "Select lastname, isAdmin "
                        + "From Registration "
                        + "Where username = ? and password = ?";
                // 3. create Statement object
                stm = conn.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                // 4. execute statement object to get result
                rs = stm.executeQuery();
                // 5. process result
                if (rs.next()) {
                    String fullName = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    dto = new RegistrationDTO(username, null, fullName, role);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return dto;
    }

    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    public void searchLastname(String searchValue)
            throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean flag = false;
        try {
            // 1. connect DB
            conn = DBHelper.makeConnection();
            if (conn != null) {
                // 2. write sql command
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration "
                        + "Where lastname LIKE ?";
                // 3. create Statement object
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                // 4. execute statement object to get result
                rs = stm.executeQuery();
                // 5. process result
                while (rs.next()) {
                    // get field/column
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    // create DTO instance
                    RegistrationDTO dto = new RegistrationDTO(username, password, lastname, role);
                    // add acount list
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    } // end account list had not existed
                    //account is avaible
                    this.accountList.add(dto);
                }
            }// end connect is
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean deleteAccount(String primayKey)
            throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        boolean flag = false;
        try {
            // 1. connect DB
            conn = DBHelper.makeConnection();
            if (conn != null) {
                // 2. write sql command
                String sql = "Delete From Registration "
                        + "Where username = ?";
                // 3. create Statement object
                stm = conn.prepareStatement(sql);
                stm.setString(1, primayKey);
                // 4. execute statement object to get result
                int effectRow = stm.executeUpdate();
                // 5. process result
                if (effectRow > 0) {
                    flag = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return flag;
    }

    public boolean updateAccount(String username, String newPass, boolean newRole)
            throws SQLException, NamingException { // update pass, role
        Connection conn = null;
        PreparedStatement stm = null;
        boolean flag = false;
        try {
            // 1. connect DB
            conn = DBHelper.makeConnection();
            if (conn != null) {
                // 2. write sql command
                String sql = "Update Registration "
                        + "Set password = ?, isAdmin = ? "
                        + "where username = ?";
                // 3. create Statement object
                stm = conn.prepareStatement(sql);
                stm.setString(1, newPass);
                stm.setBoolean(2, newRole);
                stm.setString(3, username);
                // 4. execute statement object to get result
                int effectRow = stm.executeUpdate();
                // 5. process result
                if (effectRow > 0) {
                    flag = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return flag;
    }
    
    public boolean insertAccount(RegistrationDTO dto) 
            throws NamingException, SQLException{
        boolean flag = false;
        Connection cnn = null;
        PreparedStatement pst = null;
        
        try {
            cnn = DBHelper.makeConnection();
            if(cnn != null ) {
                String sql = "Insert Registration("
                        + "username, password, lastname, isAdmin) "
                        + "Values(?,?,?,?)";
                pst = cnn.prepareStatement(sql);
                pst.setString(1, dto.getUsername());
                pst.setString(2, dto.getPassword());
                pst.setString(3, dto.getLastname());
                pst.setBoolean(4, dto.isRole());
                int effectRows = pst.executeUpdate();
                if(effectRows > 0) {
                    flag = true;
                }
            }
        } finally {
            if(pst != null) pst.close();
            if(cnn != null) cnn.close();
        }
        return flag;
    }
    
    public RegistrationDTO getAllAccountExisted(String name)
            throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        //boolean flag = false;
        RegistrationDTO dto = null;
        try {
            // 1. connect DB
            conn = DBHelper.makeConnection();
            if (conn != null) {
                // 2. write sql command
                String sql = "Select username, password, lastname, isAdmin "
                        + "From Registration Where username = ?";
                // 3. create Statement object
                stm = conn.prepareStatement(sql);
                stm.setString(1, name);
                // 4. execute statement object to get result
                rs = stm.executeQuery();
                // 5. process result
                if (rs.next()) {
                    String password = rs.getString("password");
                    String lastname = rs.getString("lastname");
                    dto = new RegistrationDTO(name, password, lastname, false);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return dto;
    }
}
