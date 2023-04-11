/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhan.registration.RegistrationDAO;
import nhan.registration.RegistrationDTO;
import nhan.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "TriggerAppServlet", urlPatterns = {"/TriggerAppServlet"})
public class TriggerAppServlet extends HttpServlet {

    //private final String LOGIN_PAGE = "login.jsp";
    //private final String SEARCH_PAGE = "search.jsp";
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
                MyApplicationConstants.TriggerAppServletFeature.LOGIN_PAGE);

        try {
            // 1. get Cookie from request 
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                // 2. get lasst cookies -> Traverse all cookies to check authentication
                Cookie lastCookie = cookies[cookies.length - 1];
                // 3. get username and password 
                String username = lastCookie.getName();
                String password = lastCookie.getValue();
                // 4. call model
                RegistrationDAO dao = new RegistrationDAO();
                RegistrationDTO checkLogin = dao.checkLogin(username, password);

                if (checkLogin != null) {
                    url = siteMaps.getProperty(
                            MyApplicationConstants.TriggerAppServletFeature.SEARCH_PAGE);

                }//end user had authenticated
            }// end cookie has exist
        } catch (NamingException e) {
            log("Error at TriggerAppServlet" + e.getMessage());
        } catch (SQLException ex) {
            log("Error at TriggerAppServlet" + ex.getMessage());
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
