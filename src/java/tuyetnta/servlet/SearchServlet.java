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
import tuyetnta.daos.ArticleDAO;
import tuyetnta.dtos.ArticleDTO;
import tuyetnta.dtos.UserDTO;
import tuyetnta.object.ListPaging;

/**
 *
 * @author tuyet
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SearchServlet.class);
    private static final String ERROR_PAGE = "error.jsp";
    private static final String SEARCH_PAGE = "search.jsp";

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
            String title = request.getParameter("txtSearchValue");
            String searchStatus = request.getParameter("statusSearch");

            //paging
            String pageS = request.getParameter("page");
            int page;
            try {
                page = Integer.parseInt(pageS);
            } catch (NumberFormatException e) {
                page = 1;
            }
            ArticleDAO dao = new ArticleDAO();
            ListPaging<ArticleDTO> listArt = null;

            //check session
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            if (title == null) {
                title = "";
            }

            if (user != null && user.getRole().getName().equals("admin")) {
                if (searchStatus != null && searchStatus.equals("all")) {
                    searchStatus = null;
                }
            } else {
                searchStatus = "active";
            }
            listArt = dao.search(title, searchStatus, page);
            request.setAttribute("ART_LIST", listArt);
            request.setAttribute("TOTAL_ROW", listArt.getTotalRow());
            request.setAttribute("TOTAL_PAGE", listArt.getTotalPage());
            request.setAttribute("NEXT", (page - 1) * ListPaging.ROW_PER_PAGE);

            url = SEARCH_PAGE;
        } catch (SQLException | NamingException | NumberFormatException ex) {
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
