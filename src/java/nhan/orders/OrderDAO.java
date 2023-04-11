/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.orders;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import nhan.product.ProductDTO;
import nhan.util.DBHelper;

/**
 *
 * @author ADMIN
 */
public class OrderDAO implements Serializable {

    public boolean checkOutProductInventory(Map<String, Integer> items,
            Map<Integer, ProductDTO> products, String orderId, String name, String address)
            throws NamingException, SQLException {
        Connection cnn = null;
        PreparedStatement pst = null;
        boolean flag = false;
        try {
            //1. cnn 
            cnn = DBHelper.makeConnection();
            double total = 0;
            if (cnn != null) {
                for (String key : items.keySet()) { // key = sku
                    ProductDTO dto = products.get(Integer.parseInt(key));
                    int quantity = items.get(key);
                    total += dto.getPrice() * quantity;
                }// end traverse items
                //2. write sql
                String sql = "Insert tbl_Order(orderID, Name, Address, dateCheckIn, total) "
                        + "VALUES (?, ?, ?, ?, ?)";
                //3. statement
                pst = cnn.prepareStatement(sql);
                pst.setString(1, orderId);
                pst.setString(2, name);
                pst.setString(3, address);
                pst.setDate(4, new Date(System.currentTimeMillis()));
                pst.setDouble(5, total);
                int rs = pst.executeUpdate();
                if (rs > 0) {
                    flag = true;
                }
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return flag;
    }
}
