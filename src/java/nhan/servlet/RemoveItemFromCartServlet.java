/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.servlet;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhan.cart.CartObject;
import nhan.cart.CartProduct;
import nhan.product.ProductDTO;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "RemoveItemFromCartServlet", urlPatterns = {"/RemoveItemFromCartServlet"})
public class RemoveItemFromCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            //1. Customer goes to cart
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. customer take a cart 
                CartObject cart = (CartObject) session.getAttribute("CART");// error o ngay day nua nha thay
                if (cart != null) {
                    //3. customer takes items
                    Map<String, Integer> items = cart.getItems();
                    if (items != null) {
                        //4. get all selected items
                        CartProduct product = (CartProduct) session.getAttribute("CART_PRODUCT");
                        if (product != null) {
                            Map<Integer, ProductDTO> products = product.getProducts();
                            if (products != null) {
                                String[] removedItems = request.getParameterValues("chkItem");
                                if (removedItems != null) {
                                    //5. remove each item from cart
                                    for (int i = 0; i < removedItems.length; i++) {
                                        String[] item = removedItems[i].split(":");
                                        String key = item[0];
                                        int quantity = Integer.parseInt(item[1]);
                                        cart.removeItemFromCart(key, quantity);
                                    }//end customer checked at least an item
                                    // end traverse each item
                                    //cap nhat lai session
                                    session.setAttribute("CART", cart);
                                    session.setAttribute("CART_PRODUCT", product);
                                }// end removerdItems had choiced
                            }
                        }
                    }// end items have existed
                }// end if cart has existed
            }// session has existed
        } finally {
            //6. refresh - call view cart again feature again using urlRewriting
//            String urlRewriting = "DispatchServlet"
//                    + "?btAction=ViewYourCart";
            String urlRewriting = "bookCartBySkuController";

            response.sendRedirect(urlRewriting);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
