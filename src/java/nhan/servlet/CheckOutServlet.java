/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhan.cart.CartObject;
import nhan.cart.CartProduct;
import nhan.orderDetail.OrderDetailDAO;
import nhan.orders.OrderDAO;
import nhan.product.ProductDTO;
import nhan.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/CheckOutServlet"})
public class CheckOutServlet extends HttpServlet {
    
    //private final String LOGIN_PAGE = "login.jsp";
    //private final String INVALID_PAGE = "invalid.html";

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

        String orderId = request.getParameter("txtOrderId");
        String name = request.getParameter("txtOrderName");
        String address = request.getParameter("txtAddress");
        
        ServletContext context = this.getServletContext();
        Properties siteMaps = (Properties) context.getAttribute("SITEMAPS");
        String url = siteMaps.getProperty(
                MyApplicationConstants.CheckOutFeature.INVALID_PAGE);

        try {
            //1. go cart place
            HttpSession session = request.getSession(false);
            if (session != null) {
                //2. take cart
                CartObject cartObj = (CartObject) session.getAttribute("CART");
                if (cartObj != null) {
                    //3. drop item into cart
                    Map<String, Integer> items = cartObj.getItems();
                    if (items != null) {
                        //4. take all items, insert into database
                        //4.1 get list of product
                        boolean flag_order = false;
                        boolean flag_orderDetail = false;
                        CartProduct cartProduct = (CartProduct) session.getAttribute("CART_PRODUCT");
                        Map<Integer, ProductDTO> products = cartProduct.getProducts();//<sku, ProductDTO>
                        if (products != null) {
                            // 4.2 insert tbl_order
                            OrderDAO dao_order = new OrderDAO();
                            flag_order = dao_order.checkOutProductInventory(items, products, orderId, name, address);
                            // 4.3 insert tbl_orderDetail
                            OrderDetailDAO dao_orderDetail = new OrderDetailDAO();
                            flag_orderDetail = dao_orderDetail.checkOutOrderDetail(items, products, orderId);
                        }// end products not null
                        if(flag_order && flag_orderDetail) {
                            // 5. delete cart -> remove attribute
                            session.removeAttribute("CART");
                            session.removeAttribute("CART_PRODUCT");
                            // 6. back login page
                            url = siteMaps.getProperty(
                                    MyApplicationConstants.CheckOutFeature.LOGIN_PAGE);
                        }
                    }
                }
            }
        } catch (NamingException e) {
            log("Error at CheckOutServlet" + e.getMessage());
        } catch (SQLException ex) {
            log("Error at CheckOutServlet" + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
