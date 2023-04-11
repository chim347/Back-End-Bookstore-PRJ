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
import nhan.registration.RegistrationCreateError;
import nhan.registration.RegistrationDAO;
import nhan.registration.RegistrationDTO;
import nhan.util.MyApplicationConstants;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CreateNewAccountServlet", urlPatterns = {"/CreateNewAccountServlet"})
public class CreateNewAccountServlet extends HttpServlet {
    
    //public final String LOGIN_PAGE = "login.jsp";
    //public final String CREATE_NEW_ACCOUNT_PAGE = "createNewAccount.jsp";
    

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
        String fullname = request.getParameter("txtFullname");
        String confirm = request.getParameter("txtConfirm");
        boolean foundError = false;
        String url = siteMaps.getProperty(
                MyApplicationConstants.CreateNewAccountFeature.CREATE_NEW_ACCOUNT_PAGE);
        RegistrationCreateError error = new RegistrationCreateError();
        RegistrationDAO dao = new RegistrationDAO();
        
        try {    
            //1. check all user's constraints
            if (username.trim().length() < 6 || username.trim().length() > 20) {
                foundError = true;
                error.setUsernameLengthError("Username is required input 6 - 20 character");
            }
//            if(dao.getAllAccountExisted(username) != null) {
//                foundError = true;
//                error.setUsernameIsExisted(username + " usernaem is existed");
//            }
            if (password.trim().length() < 6 || password.trim().length() > 20) {
                foundError = true;
                error.setPasswordLengthError("Password is required input 6 - 20 character");
            }
            if (fullname.trim().length() < 6 || fullname.trim().length() > 50) {
                foundError = true;
                error.setFullnameLengthError("Fullname is required input 6 - 50 character");
            }
            if (!confirm.trim().equals(password.trim())) {
                foundError = true;
                error.setConfirmNotMacthed("confirm must matches password");
            }
            if (foundError) {
                //2. store errors and fw to error page
                request.setAttribute("CREATE_ERROR", error);
            } else {
                //3. call DAO
                RegistrationDTO dto = new RegistrationDTO(username, password, fullname, false);
                boolean result = dao.insertAccount(dto);
                if(result) {
                    url = siteMaps.getProperty(MyApplicationConstants.CreateNewAccountFeature.LOGIN_PAGE);
                }
            }
        } catch (SQLException ex) {
            String msg = ex.getMessage();
            log("CreateNewAccountServlet _ SQL" + msg);
            if(msg.contains("duplicate")) {
                error.setUsernameIsExisted(username + " is existed!!");
                request.setAttribute("CREATE_ERROR", error);
            }
        } catch (NamingException e) {
            log("CreateNewAccountServlet _ Naming " + e.getMessage());
        } finally {
            // sai respones thi no se huy mat vung nho cua minh
            // vi minh dang luu setAttribute trong reqScope
            request.getRequestDispatcher(url).forward(request, response);
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
