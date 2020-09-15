/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuyetnta.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import tuyetnta.daos.UserDAO;
import tuyetnta.dtos.RoleDTO;
import tuyetnta.dtos.UserDTO;
import tuyetnta.utils.PasswordEncrypt;
import tuyetnta.utils.Validator;

/**
 *
 * @author tuyet
 */
@WebServlet(name = "RegistraionAccountServlet", urlPatterns = {"/RegistraionAccountServlet"})
public class RegistraionAccountServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(RegistraionAccountServlet.class);
    private static final String SEARCH_CONTROLLER = "SearchServlet";
    private static final String ERROR_PAGE = "error.jsp";
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String REGISTER_PAGE = "register.jsp";

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
        String url = SEARCH_CONTROLLER;
        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String fullname = request.getParameter("txtFullname");
            String status = "new";
            if (email == null || email.isEmpty() || password == null || password.isEmpty() || fullname == null || fullname.isEmpty()) {
                request.setAttribute("MSG_ERROR", "Please input fied");
                url = REGISTER_PAGE;
                return;
            }
            boolean hasError = false;
            if (!Validator.check(email, Validator.EMAIL_PATTERN)) {
                request.setAttribute("EMAIL_PATTERN", "Invalid format email");
                hasError = true;
            }
            if (!Validator.check(password, Validator.PASSWORD_PATTERN)) {
                request.setAttribute("PASSWORD_PATTERN", "Password only alpha numberics and in rang 6-30 char");
                hasError = true;
            }
            if (!Validator.check(fullname, Validator.FULLNAME_PATTERN)) {
                request.setAttribute("FULLNAME_PATTERN", "Full name only alphabet char and in rang 3-50 char");
                hasError = true;
            }
            if (hasError) {
                url = REGISTER_PAGE;
                return;
            }
            UserDAO dao = new UserDAO();
            UserDTO user = new UserDTO();
            user.setEmail(email);
            user.setFullname(fullname);
            user.setPassword(PasswordEncrypt.getEncryptedPassword(password));
            RoleDTO Myrole = new RoleDTO();
            Myrole.setId(2);
            user.setRole(Myrole);
            user.setStatus("new");
            boolean result = dao.add(user);
            if (result) {
                url = LOGIN_PAGE;
            } else {
                request.setAttribute("MSG_ERROR", "Regist failed");
                url = REGISTER_PAGE;
            }

        } catch (SQLException | NamingException | NoSuchAlgorithmException ex) {
            String msg = ex.getMessage();
            if (msg.contains("duplicate")) {
                request.setAttribute("DUPLICATE", "Your email is existed");
                url = REGISTER_PAGE;
                return;
            }
            LOGGER.error(msg);
            request.setAttribute("ERROR", "Some thing was wrong");
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
