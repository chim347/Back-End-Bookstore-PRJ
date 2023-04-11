/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.product;

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
public class ProductDAO implements Serializable {

    private List<ProductDTO> productList;

    public List<ProductDTO> getProductList() {
        return productList;
    }

    public void getItemFromInventory()
            throws NamingException, SQLException {
        Connection cnn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            //1.cn database
            cnn = DBHelper.makeConnection();
            if (cnn != null) {
                //2. write sql query
                String sql = "Select SKU, NameProduct, Description, price, quantity, status "
                        + "From tbl_Product "
                        + "Where status = 1";
                //3. preparestament 
                pst = cnn.prepareStatement(sql);
                //4. resultSet
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (Integer.parseInt(rs.getString("quantity")) > 0) {
                        int sku = rs.getInt("SKU");
                        String name = rs.getString("NameProduct");
                        String des = rs.getString("Description");
                        float price = rs.getFloat("price");
                        int quantity = rs.getInt("quantity");
                        boolean status = rs.getBoolean("status");

                        ProductDTO dto = new ProductDTO(sku, name, des, price, quantity, status);
                        if (this.productList == null) {
                            productList = new ArrayList<>();
                        }
                        this.productList.add(dto);
                    }
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

    public ProductDTO getItemsById(String sku) throws NamingException, SQLException {
        Connection cnn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ProductDTO dto = null;
        try {
            //1. make cnn
            cnn = DBHelper.makeConnection();
            if (cnn != null) {
                //2. wwrite sql
                String sql = "Select SKU, NameProduct, Description, price, quantity, status "
                        + "From tbl_Product "
                        + "Where SKU = ?";
                //3. create preparesatment
                pst = cnn.prepareStatement(sql);
                pst.setInt(1, Integer.parseInt(sku));
                rs = pst.executeQuery();
                while (rs.next()) {
                    int skU = rs.getInt("SKU");
                    String name = rs.getString("NameProduct");
                    String des = rs.getString("Description");
                    float price = rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    boolean status = rs.getBoolean("status");
                    dto = new ProductDTO(skU, name, des, price, quantity, status);
                }
            }
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if(rs != null)  rs.close();
            if(pst != null)  pst.close();
            if(cnn != null)  cnn.close();
        }
        return dto;
    }
}
