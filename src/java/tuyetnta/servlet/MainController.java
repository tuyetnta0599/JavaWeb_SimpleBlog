/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuyetnta.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author tuyet
 */
public class MainController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MainController.class);
    private static final String ERROR_PAGE = "error.jsp";
    private static final String LOGIN_CONTROLLER = "LoginServlet";
    private static final String LOGOUT_CONTROLLER = "LogoutServlet";
    private static final String SEARCH_CONTROLLER = "SearchServlet";
    private static final String DELETE_ARTICLE_CONTROLLER = "DeleteAritcleServlet";
    private static final String ACCEPT_ARTICLE_CONTROLLER = "AcceptArticleServlet";
    private static final String ARTICLE_DETAIL_CONTROLLER = "AritcleDetailServlet";
    private static final String REGISTRATION_ACCOUNT_CONTROLLER = "RegistraionAccountServlet";
    private static final String POST_THE_ARTICLE_CONTROLLER = "PostTheAritcleServlet";
    private static final String POST_COMMENT_ARTICLE_CONTROLLER = "CommentArticleServlet";
    private static final String LOAD_USER_CONTROLLER = "GetAllUserServlet";

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
        request.setCharacterEncoding("UTF-8");
        String url = ERROR_PAGE;
        try {
            String action = request.getParameter("btnAction");
            if (action == null) {
                request.setAttribute("ERROR", "Invalid Action");
                url = ERROR_PAGE;
                return;
            }
            if (action.equals("Login")) {
                url = LOGIN_CONTROLLER;
            } else if (action.equals("Logout")) {
                url = LOGOUT_CONTROLLER;
            } else if (action.equals("Search")) {
                url = SEARCH_CONTROLLER;
            } else if (action.equals("Delete")) {
                url = DELETE_ARTICLE_CONTROLLER;
            } else if (action.equals("Accept")) {
                url = ACCEPT_ARTICLE_CONTROLLER;
            } else if (action.equals("Detail")) {
                url = ARTICLE_DETAIL_CONTROLLER;
            } else if (action.equals("Register")) {
                url = REGISTRATION_ACCOUNT_CONTROLLER;
            } else if (action.equals("Post Article")) {
                url = POST_THE_ARTICLE_CONTROLLER;
            } else if (action.equals("Post Comment")) {
                url = POST_COMMENT_ARTICLE_CONTROLLER;
            } else if (action.equals("Load User")) {
                url = LOAD_USER_CONTROLLER;
            } else {
                request.setAttribute("ERROR", "Invalid Action");
                url = ERROR_PAGE;
            }
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
