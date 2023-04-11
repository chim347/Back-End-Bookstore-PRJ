/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import nhan.product.ProductDTO;

/**
 *
 * @author ADMIN
 */
public class CartProduct implements Serializable {

    private Map<Integer, ProductDTO> products;

    public Map<Integer, ProductDTO> getProducts() {
        return this.products;
    }

    public void addItemProductToCart(ProductDTO dto) {
        if (this.products == null) {
            this.products = new HashMap<>();
        }
        this.products.put(dto.getSku(), dto);
    }

    public boolean addItemProductToCartOneProduct(ProductDTO dto) {

        boolean result = false;
        if (this.products.get(dto.getSku()) == null) {
            return result;
        }
        if (this.products.get(dto.getSku()).getQuantity() <= 0) {
            return result;
        }
        //1. check existed items
        if (this.products == null) {
            this.products = new HashMap<>();
        }
        //2. check existed item
        if (!this.products.containsKey(dto.getSku())) {
            this.products.put(dto.getSku(), dto);
            result = true;
        }
        //3. when items has existed, check existed id
        int currentQuantity = this.products.get(dto.getSku()).getQuantity();
        int newQuantity = currentQuantity + 1;
        if (newQuantity > 1) {
            this.products.get(dto.getSku()).setQuantity(newQuantity);
            result = true;
        }
        return result;
    }

    public boolean removeProductToList(ProductDTO dto) {
        boolean result = false;
        if (this.products.get(dto.getSku()) == null) {
            return result;
        }
        if (this.products.get(dto.getSku()).getQuantity() <= 0) {
            return result;
        }
        //1. check existed items
        if (this.products == null) {
            return result;
        }
        //2. check existed item
        if (!this.products.containsKey(dto.getSku())) {
            return result;
        }
        //3. when items has existed, check existed id
        int currentQuantity = this.products.get(dto.getSku()).getQuantity();
        int quantity = this.products.get(dto.getSku()).getQuantity();
        if (currentQuantity >= this.products.get(dto.getSku()).getQuantity()) {
            quantity = currentQuantity - quantity;
        } else {
            return result;
        }
        if (quantity == 0) {
            this.products.remove(this.products.get(dto.getSku()).getSku());
            if (this.products.isEmpty()) { // check 1 lan nua xem co bi null ko
                this.products = null;
            }
        } else {
            this.products.put(this.products.get(dto.getSku()).getSku(), dto);
        }
        result = true;

        return result;
    }
}
