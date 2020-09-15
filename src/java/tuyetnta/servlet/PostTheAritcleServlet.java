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
import tuyetnta.utils.Validator;

/**
 *
 * @author tuyet
 */
@WebServlet(name = "PostTheAritcleServlet", urlPatterns = {"/PostTheAritcleServlet"})
public class PostTheAritcleServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(PostTheAritcleServlet.class);
    private static final String ERROR_PAGE = "error.jsp";
    private static final String POST_ARTICLE_PAGE = "postArticle.jsp";

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
        String url = POST_ARTICLE_PAGE;
        try {
            String title = request.getParameter("txtTitle");
            String content = request.getParameter("txtContent");
            String shortDescription = request.getParameter("txtShortDescription");
            if (title == null || title.isEmpty() || content == null || content.isEmpty() || shortDescription == null || shortDescription.isEmpty()) {
                request.setAttribute("MSG_ERROR", "Please fill all field");
                return;
            }
            if (!Validator.checkLenght(title, Validator.TITLE_MIN_LENGTH, Validator.TITLE_MAX_LENGTH)) {
                request.setAttribute("TITLE_ERROR", "Title must in range 3-100 character");
                return;
            }
            if (!Validator.checkLenght(shortDescription, Validator.SHORTDES_MIN_LENGTH, Validator.SHORTDES_MAX_LENGTH)) {
                request.setAttribute("SD_ERROR", "Short description must in range 3-100");
                return;
            }
            HttpSession session = request.getSession();
            UserDTO user = (UserDTO) session.getAttribute("USER");
            ArticleDAO dao = new ArticleDAO();
            ArticleDTO art = new ArticleDTO();
            art.setTitle(title);
            art.setContent(content);
            art.setShortDescription(shortDescription);
            art.setAuthor(user);
            boolean create = dao.create(art);
            if (create) {
                request.setAttribute("MSG_SUCCESS", "Post the article successfull,, Please wait for admin accept your post");
            } else {
                request.setAttribute("MSG_ERROR", "Post the article failed");
            }
        } catch (SQLException | NamingException ex) {
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
