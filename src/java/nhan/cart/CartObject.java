/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class CartObject implements Serializable {

    private Map<String, Integer> items;

    public Map<String, Integer> getItems() {
        return items;
    }

    public boolean addItemToCart(String id, int quantity) {
        boolean result = false;
        //1. check data validation
        if (id == null) {
            return result;
        }
        if (id.trim().isEmpty()) {
            return result;
        }
        if (quantity < 0) {
            return result;
        }
        //2. when item is existed, checking existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3. when items has existed, checking existed id
        if (this.items.containsKey(id)) {
            int currentQuantity = this.items.get(id);
            quantity = currentQuantity + 1;// error
        } //end items existed
        //4. update items
        this.items.put(id, quantity);
        result = true;

        return result;
    }

    public boolean addItemToCartRemove(String id, int quantity) {
        boolean result = false;
        //1. check data validation
        if (id == null) {
            return result;
        }
        if (id.trim().isEmpty()) {
            return result;
        }
        if (quantity <= 0) {
            return result;
        }
        //2. when item is existed, checking existed items
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        //3. when items has existed, checking existed id
        if (this.items.containsKey(id)) {
            int currentQuantity = this.items.get(id);
            if (quantity >= currentQuantity) {
                quantity = quantity - currentQuantity;// error
            } else {
                return result;
            }
            if (quantity == 0) {
                this.items.remove(id);
                if (this.items.isEmpty()) { // check 1 lan nua xem co bi null ko
                    this.items = null;
                }
            }
        } //end items existed
        //4. update items
        this.items.put(id, quantity);
        result = true;

        return result;
    }

    public boolean removeItemFromCart(String id, int quantity) {
        boolean result = false;
        if (id == null) {
            return result;
        }
        if (id.trim().isEmpty()) {
            return result;
        }
        if (quantity <= 0) {
            return result;
        }
        //1. check existed items
        if (this.items == null) {
            return result;
        }
        //2. check existed item
        if (!this.items.containsKey(id)) {
            return result;
        }
        //3. when items has existed, check existed id
        int currentQuantity = this.items.get(id);
        if (currentQuantity >= quantity) {
            quantity = currentQuantity - quantity;
        } else {
            return result;
        }
        if (quantity == 0) {
            this.items.remove(id);
            if (this.items.isEmpty()) { // check 1 lan nua xem co bi null ko
                this.items = null;
            }
        } else {
            this.items.put(id, quantity);
        }
        result = true;

        return result;
    }
}
