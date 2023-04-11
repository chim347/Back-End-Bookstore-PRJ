/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhan.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(name = "SearchLastNameServlet", urlPatterns = {"/SearchLastNameServlet"})
public class SearchLastNameServlet extends HttpServlet {

    //private final String SEARCH_PAGE = "search.html";
    //private final String SEARCH_RESULT_PAGE = "search.jsp";

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
        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
        
        String searchValue = request.getParameter("txtSearchValue").trim();
        String url = siteMaps.getProperty(
                MyApplicationConstants.SearchLastNameFeature.SEARCH_PAGE);
        try {
            if (searchValue.trim().isEmpty()) {
                // search not trim or empty
                // ko làm gì thì load lại trang search
            } else {
                // call DAO
                // 4.1 new object
                RegistrationDAO dao = new RegistrationDAO();
                // 4.2 call method
                dao.searchLastname(searchValue);
                
                // 4.3 store result to scope (if any)
                List<RegistrationDTO> result = dao.getAccountList();
                url = siteMaps.getProperty(
                        MyApplicationConstants.SearchLastNameFeature.SEARCH_RESULT_PAGE);
                request.setAttribute("SEARCH_RESULT", result);
            }//end searchValue has inpuuted valid value
        } catch (NamingException ex) {
            log("Error at SearchLastNameServlet" + ex.getMessage());
        } catch (SQLException e) {
            log("Error at SearchLastNameServlet" + e.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
