/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuyetnta.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tuyetnta.daos.UserDAO;
import tuyetnta.dtos.UserDTO;
import tuyetnta.utils.PasswordEncrypt;

/**
 *
 * @author tuyet
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

    Pattern emailPattern = Pattern.compile("([a-zA-Z0-9]{3,50})@([a-zA-Z]{3,10})((\\.([a-zA-Z]{2,5})){1,2})");
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String ERROR_PAGE = "error.jsp";
    private static final String SEARCH_CONTROLLER = "SearchServlet";

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
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                request.setAttribute("MSG_ERROR", "Please enter email and password");
                url = LOGIN_PAGE;
            } else {
                if (!emailPattern.matcher(email).matches()) {
                    request.setAttribute("MSG_ERROR", "Invalid format email");
                    url = LOGIN_PAGE;
                } else {
                    UserDAO dao = new UserDAO();
                    UserDTO user = dao.checkLogin(email, PasswordEncrypt.getEncryptedPassword(password));
                    if (user != null) {
                        HttpSession session = request.getSession();
                        session.setAttribute("USER", user);
                        String viewArt = request.getParameter("viewArt");
                        if (viewArt != null && !viewArt.isEmpty()) {
                            url = "MainController?btnAction=Detail&txtID=" + viewArt;
                            return;
                        }
                        url = SEARCH_CONTROLLER;
                    } else {
                        request.setAttribute("MSG_ERROR", "Email or password incorrect");
                        url = LOGIN_PAGE;
                    }
                }
            }
        } catch (NamingException | SQLException | NoSuchAlgorithmException ex) {
            LOGGER.error(ex.getMessage());
            request.setAttribute("ERROR", ex.getMessage());
            url = ERROR_PAGE;
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
