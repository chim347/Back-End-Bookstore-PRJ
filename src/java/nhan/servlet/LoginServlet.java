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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nhan.registration.RegistrationDAO;
import nhan.registration.RegistrationDTO;
import nhan.registration.RegistrationLoginError;
import nhan.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {

    //private final String INVALID_PAGE = "login.jsp";
    //private final String SEARCH_PAGE = "search.jsp";
    //public static final String INVALID_PAGE = "loginPage";
    //public static final String SEARCH_PAGE = "search.jsp";

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
        
        String url = siteMaps.getProperty(
                MyApplicationConstants.LoginFeature.LOGIN_PAGE);
        String username = request.getParameter("txtUsername").trim();
        String password = request.getParameter("txtPassword");
        boolean foundError = false;
        RegistrationLoginError errors = new RegistrationLoginError();

        try {
            if (username.isEmpty()) {
                foundError = true;
                errors.setUserNameEmpty("Username must not empty bro");
            }
            if (password.isEmpty()) {
                foundError = true;
                errors.setPasswordEmpty("password must not empty broooo");
            }
            if (foundError) {
                request.setAttribute("ERROR_LOGIN", errors);
            } else {
                //3.call model
                //3.1 new object
                RegistrationDAO dao = new RegistrationDAO();
                //3.2 call method from that object
                RegistrationDTO checkLogin = dao.checkLogin(username, password);
                //4. send view
                if (checkLogin != null) {
                    url = siteMaps.getProperty(
                            MyApplicationConstants.LoginFeature.SEARCH_PAGE);
                    // add cookie to client using resObj
                    HttpSession session = request.getSession();
                    session.setAttribute("USER", checkLogin);
//                Cookie cookie = new Cookie(username, password); //day chi la demo, lam that phai co hashcode 
//                cookie.setMaxAge(60 * 3);
//                response.addCookie(cookie);
                }else {
                    errors.setAcountNotFound("UserName/Password wrong, please again!!!");
                    request.setAttribute("ERROR_LOGIN", errors);
                }
            }
        } catch (NamingException ex) {
            log("Error at LoginServlet" + ex.getMessage());
        } catch (SQLException e) {
            log("Error at LoginServlet" + e.getMessage());
        } finally {  
            // 5. set values to res obj
            //response.sendRedirect(url);
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
