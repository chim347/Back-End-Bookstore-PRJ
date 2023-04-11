/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.orders;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class OrderDTO implements Serializable{
    
    private String orderId;
    private String name;
    private String address;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String name, String address) {
        this.orderId = orderId;
        this.name = name;
        this.address = address;
    }
    
    /**
     * @return the orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    
    
    
    
}
