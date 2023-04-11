///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package nhan.servlet;
//
//import java.io.IOException;
//import java.util.Properties;
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import nhan.util.MyApplicationConstants;
//
///**
// *
// * @author ADMIN
// */
//@WebServlet(name = "DispatchServlet", urlPatterns = {"/DispatchServlet"})
//public class DispatchServlet extends HttpServlet {
//
////    private final String LOGIN_PAGE = "login.jsp";
////    private final String LOGIN_CONTROLLER = "LoginServlet";
////    private final String LOGOUT_CONTROLLER = "LogoutServlet";
////    private final String SEARCH_LASTNAME_CONTROLLER = "SearchLastNameServlet";    
////    private final String DELETE_ACCOUNT_CONTROLLER = "DeleteAccountServlet";
////    private final String UPDATE_ACCOUNT_CONTROLLER = "UpdateAccountServlet";
////    private final String TRIGGER_APP_CONTROLLER = "TriggerAppServlet";
////    private final String ADD_ITEM_TO_CART_CONTROLLER = "AddItemToCartServlet";
////    private final String REMOVE_ITEM_FROM_CART_CONTROLLER = "RemoveItemFromCartServlet";
////    private final String CREATE_NEW_ACCOUNT_CONTROLLER = "CreateNewAccountServlet";
////    private final String CHECK_OUT_FROM_CART_CONTROLLER = "CheckOutServlet";
////    private final String VIEW_CART_PAGE = "BookCartBySkuServlet";
//
//    
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        
//        ServletContext context = this.getServletContext();
//        Properties siteMaps = (Properties)context.getAttribute("SITEMAPS");
//        // which button did user clicked???
//        String button = request.getParameter("btAction");
//        
//        //String url = LOGIN_PAGE;
//        String url = siteMaps.getProperty(
//                MyApplicationConstants.DispatchFeature.LOGIN_PAGE);
//        
//        try {
//            if(button == null) {
//                // transfer to Login PAge
//                // check cookie
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.DispatchFeature.TRIGGER_APP_CONTROLLER);
//            }else if(button.equals("Login")) {
//                //url = LOGIN_CONTROLLER;
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.DispatchFeature.LOGIN_CONTROLLER);
//            }else if(button.equals("Search")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.DispatchFeature.SEARCH_LASTNAME_CONTROLLER);
//            }else if(button.equals("delete")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.DispatchFeature.DELETE_ACCOUNT_CONTROLLER);
//            }else if(button.equals("Update")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.DispatchFeature.UPDATE_ACCOUNT_CONTROLLER);
//            }else if(button.equals("AddItemToYourCart")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.DispatchFeature.ADD_ITEM_TO_CART_CONTROLLER);
//            }else if(button.equals("ViewYourCart")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.DispatchFeature.VIEW_CART_CONTROLLER);
//            }else if(button.equals("RemoveSelectedItems")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.DispatchFeature.REMOVE_ITEM_FROM_CART_CONTROLLER);
//            }else if(button.equals("CreateNewAccount")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.DispatchFeature.CREATE_NEW_ACCOUNT_CONTROLLER);
//            }else if(button.equals("Check out")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.DispatchFeature.CHECK_OUT_FROM_CART_CONTROLLER);
//            }else if(button.equals("Logout")) {
//                url = siteMaps.getProperty(
//                        MyApplicationConstants.DispatchFeature.LOGOUT_CONTROLLER);
//            }
//        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
