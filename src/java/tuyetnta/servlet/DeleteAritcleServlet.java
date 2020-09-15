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
import org.apache.log4j.Logger;
import tuyetnta.daos.ArticleDAO;
import tuyetnta.dtos.ArticleDTO;

/**
 *
 * @author tuyet
 */
@WebServlet(name = "DeleteAritcleServlet", urlPatterns = {"/DeleteAritcleServlet"})
public class DeleteAritcleServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(DeleteAritcleServlet.class);
    private static final String SEARCH_CONTROLLER = "SearchServlet";
    private static final String ERROR_PAGE = "error.jsp";

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
        String url = SEARCH_CONTROLLER;
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
            ArticleDTO art = dao.updateStatus(id, "deleted", "new");
            if (art == null) {
                request.setAttribute("MSG_ERROR", "ID not exits or activated");
                return;
            } else {
                request.setAttribute("MSG_SUCCESS", "Delete article : " + art.getTitle() + " successfull");
            }
        } catch (NamingException | SQLException ex) {
            LOGGER.error(ex.getMessage());
            request.setAttribute("ERROR", "Something was wrong");
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
