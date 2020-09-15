/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuyetnta.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import tuyetnta.daos.ArticleCommentDAO;
import tuyetnta.daos.ArticleDAO;
import tuyetnta.dtos.ArticleCommentDTO;
import tuyetnta.dtos.ArticleDTO;

/**
 *
 * @author tuyet
 */
@WebServlet(name = "AritcleDetailServlet", urlPatterns = {"/AritcleDetailServlet"})
public class AritcleDetailServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AritcleDetailServlet.class);
    private static final String ERROR_PAGE = "error.jsp";
    private static final String DETAIL_PAGE = "detail.jsp";
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
        String url = DETAIL_PAGE;
        try {
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
            ArticleDAO dao = new ArticleDAO();
            ArticleDTO art = dao.getArticle(id);
            if (art == null) {
                request.setAttribute("MSG_ERROR", "ID is not exist");
                url = SEARCH_CONTROLLER;
                return;
            }
            ArticleCommentDAO commentDAO = new ArticleCommentDAO();
            List<ArticleCommentDTO> listcomment = commentDAO.getAllArticleComment(id);
            request.setAttribute("ART_COMMENT", listcomment);
            request.setAttribute("ART_DETAIL", art);
            url = DETAIL_PAGE;
        } catch (NamingException | SQLException ex) {
            LOGGER.error(ex.getMessage());
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
