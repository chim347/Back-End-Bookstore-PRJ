/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.orderDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import nhan.product.ProductDTO;
import nhan.util.DBHelper;

/**
 *
 * @author ADMIN
 */
public class OrderDetailDAO implements Serializable {

    public boolean checkOutOrderDetail(Map<String, Integer> items, Map<Integer, ProductDTO> products, String orderID)
            throws NamingException, SQLException {
        Connection cnn = null;
        PreparedStatement pst = null;
        boolean flag = false;
        double total = 0;
        int quantity = 0;
        double price = 0;
        int sku = 0;
        try {
            //1. make cnn
            cnn = DBHelper.makeConnection();
            if (cnn != null) {
                cnn.setAutoCommit(false); // false: if wrong inset, update, delete -> can rollback
                // true: can't rollback()
                //2. write sql
                String sql = "Insert tbl_OrderDetail(orderID, SKU, quantity, price, total) "
                        + "VALUES (?, ?, ?, ?, ?)";
                pst = cnn.prepareStatement(sql);

                for (String key : items.keySet()) { // sku : key
                    ProductDTO dto = products.get(Integer.parseInt(key));
                    sku = Integer.parseInt(key);
                    quantity = items.get(key);
                    price = dto.getPrice();
                    total = price * quantity;

                    pst.setString(1, orderID);
                    pst.setInt(2, sku);
                    pst.setInt(3, quantity);
                    pst.setDouble(4, price);
                    pst.setDouble(5, total);
                    pst.addBatch();
                }// end travese items

                int[] rows = null;
                if (pst != null) {
                    rows = pst.executeBatch();
                }
                if (rows != null) {
                    flag = true;
                    for (int row : rows) {
                        if (row <= 0) {
                            flag = false;
                            break;
                        }// end row <= 0
                    }// end traverse rows
                }// end rows not null
                if (flag) {
                    cnn.commit();
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            if (cnn != null) {
                cnn.rollback();
            }
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
