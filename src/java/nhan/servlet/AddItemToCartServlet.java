/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.servlet;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhan.cart.CartObject;
import nhan.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddItemToCartServlet", urlPatterns = {"/AddItemToCartServlet"})
public class AddItemToCartServlet extends HttpServlet {

    //private final String SHOPPING_PAGE = "onlineShopping.html";  //1. for static page
    
    //private final String SHOPPING_PAGE = "ShoppingPageServlet";   // 2.
    
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
        
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        
        String url = siteMaps.getProperty(
                MyApplicationConstants.AddItemToCartFeature.SHOPPING_PAGE);
        try {
            /* Add item for demo static data
            //1. Customer goes cart place
            HttpSession session = request.getSession(); // bat buoc co gio, ko can check, vi true tu tao gio hang roi
            //2. customer take a cart
            CartObject cart = (CartObject) session.getAttribute("CART");
            if(cart == null)
                cart = new CartObject(); 
            //3. Customer drop item to cart
            String item = request.getParameter("cboBook");
            //4. customer drops item to cart
            cart.addItemToCart(item, 1);
            //5. cap nhat hang trong kho
            session.setAttribute("CART", cart); */
            
            //1. Customer goes cart place
            HttpSession session = request.getSession();
            //2. customer take a cart
            CartObject cartObj = (CartObject)session.getAttribute("CART");
            if(cartObj == null) {
                cartObj = new CartObject();
            }
            //3. customer drop item to cart
            String item = request.getParameter("txtSku");
            String quantity = request.getParameter("txtQuanity");
            //4. customer drops item to cart
            cartObj.addItemToCart(item, Integer.parseInt(quantity));
            //5. cap nhat lai gio hang
            session.setAttribute("CART", cartObj);
        } finally {
            //6. redirect to onlin shopping page
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            //response.sendRedirect(url);
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
