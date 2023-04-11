/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nhan.registration.RegistrationDAO;
import nhan.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "UpdateAccountServlet", urlPatterns = {"/UpdateAccountServlet"})
public class UpdateAccountServlet extends HttpServlet {

    //private final String ERROR_PAGE = "invalid.html";
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

        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String lastSearchValue = request.getParameter("lastSearchValue");
        String urlRewriting = siteMaps.getProperty(
                MyApplicationConstants.UpdateAccountFeature.ERROR_PAGE);
        boolean role = false;
        if (request.getParameter("chkAdmin") != null) {
            role = true;
        }
        try {
            // call model
            RegistrationDAO dao = new RegistrationDAO();
            boolean flag = dao.updateAccount(username, password, role);
            if (flag) {
//                urlRewriting = "DispatchController"
//                        + "?btAction=Search"
//                        + "&txtSearchValue=" + lastSearchValue;
                urlRewriting = "searchLastNameController" 
                        + "?txtSearchValue=" + lastSearchValue;
            }
        } catch (NamingException e) {
            log("Error at UpdateServlet" + e.getMessage());
        } catch (SQLException ex) {
            log("Error at UpdateServlet" + ex.getMessage());
        } finally {
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
