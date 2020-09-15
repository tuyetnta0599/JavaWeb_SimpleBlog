/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuyetnta.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import tuyetnta.daos.ArticleCommentDAO;
import tuyetnta.dtos.UserDTO;

/**
 *
 * @author tuyet
 */
@WebServlet(name = "CommentArticleServlet", urlPatterns = {"/CommentArticleServlet"})
public class CommentArticleServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(CommentArticleServlet.class);
    private static final String ERROR_PAGE = "error.jsp";
    private static final String ARTICLE_DETAIL_CONTROLER = "AritcleDetailServlet";
    private static final String LOGIN_PAGE = "login.jsp";

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
        String url = ARTICLE_DETAIL_CONTROLER;
        try {
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (user==null) {
                request.setAttribute("MSG_ERROR", "Login to comment the posts");
                request.setAttribute("viewArt", request.getParameter("txtID"));
                url = LOGIN_PAGE;
                return;
            }
            String comment = request.getParameter("txtComment");
            String idS = request.getParameter("txtID");
            Integer id;
            try {
                id = Integer.parseInt(idS);
            } catch (NumberFormatException e) {
                id = null;
            }
            if (id == null) {
                request.setAttribute("MSG_ERROR", "ID must be number");
                return;
            }
            if (comment == null || comment.isEmpty()) {
                request.setAttribute("MSG_ERROR", "Please comment some thing");
                return;
            }
            ArticleCommentDAO dao = new ArticleCommentDAO();

            if (!user.getRole().getName().equals("member")) {
                url = LOGIN_PAGE;
                return;
            }
            if (user.getRole().getName().equals("admin")) {
                url = ERROR_PAGE;
                request.setAttribute("ERROR", "Admin can not do this action");
                return;
            }
            if (dao.addComment(id, comment, user.getEmail())) {
                request.setAttribute("MSG_SUCCESS", "Comment success");
            }
        } catch (SQLException | NamingException e) {
            LOGGER.error(e.getMessage());
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
