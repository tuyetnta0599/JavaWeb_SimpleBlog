/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuyetnta.daos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.naming.NamingException;
import tuyetnta.dtos.ArticleDTO;
import tuyetnta.dtos.UserDTO;
import tuyetnta.object.ListPaging;
import tuyetnta.utils.MyConnection;

/**
 *
 * @author tuyet
 */
public class ArticleDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement prStm = null;
    private ResultSet rs = null;

    private void closeConn() throws SQLException {
        try {
            if (rs != null) {
                rs.close();
            }
            if (prStm != null) {
                prStm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
        }

    }

    public ArticleDTO updateStatus(int id, String status, String statusCondition) throws NamingException, SQLException {
        ArticleDTO artEffect = null;
        try {
            conn = MyConnection.getMyConnection();
            conn.setAutoCommit(false);
            //select article
            String sqlSelect = "SELECT title FROM dbo.articles WHERE id = ? AND status = ?";
            prStm = conn.prepareStatement(sqlSelect);
            prStm.setInt(1, id);
            prStm.setString(2, statusCondition);
            rs = prStm.executeQuery();
            if (!rs.next()) {
                return null;
            }
            artEffect = new ArticleDTO();
            artEffect.setTitle(rs.getNString("title"));
            artEffect.setId(id);

            //update article
            String sqlUpdate = "UPDATE dbo.articles SET status = ? WHERE id = ? AND status = ?";
            prStm = conn.prepareStatement(sqlUpdate);
            prStm.setString(1, status);
            prStm.setInt(2, id);
            prStm.setString(3, statusCondition);
            int rowEffect = prStm.executeUpdate();
            if (rowEffect < 1) {
                return null;
            }
            conn.commit();
        } finally {
            closeConn();
        }
        return artEffect;
    }

    public boolean create(ArticleDTO article) throws SQLException, NamingException {
        boolean check = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "INSERT INTO dbo.articles ( title , content , shortDescription , author , postingDate , status ) VALUES (?, ?, ?, ?, GETDATE(), 'new')";
            prStm = conn.prepareStatement(sql);
            prStm.setNString(1, article.getTitle());
            prStm.setNString(2, article.getContent());
            prStm.setNString(3, article.getShortDescription());
            prStm.setString(4, article.getAuthor().getEmail());
            int rowEffect = prStm.executeUpdate();
            if (rowEffect > 0) {
                check = true;
            }
        } finally {
            closeConn();
        }
        return check;
    }

    public ListPaging<ArticleDTO> search(String title, String status, int page) throws SQLException, NamingException {
        ListPaging<ArticleDTO> listArt = new ListPaging<>();
        try {
            conn = MyConnection.getMyConnection();
            String sql = "SELECT  a.id ,\n"
                    + "        a.title ,\n"
                    + "        a.shortDescription ,\n"
                    + "        u.fullname AS authorName ,\n"
                    + "        a.postingDate ,\n"
                    + "        a.status,\n"
                    + "		totalRow = COUNT(*) OVER()\n"
                    + "FROM    dbo.articles AS a\n"
                    + "        JOIN dbo.users AS u ON u.email = a.author\n"
                    + "WHERE   a.title LIKE ? COLLATE Latin1_General_CI_AI\n"
                    + "        AND (? IS NULL OR a.status = ?)\n"
                    + "		ORDER BY a.postingDate DESC\n"
                    + "		OFFSET ? ROW FETCH NEXT ? ROW ONLY";
            prStm = conn.prepareStatement(sql);
            prStm.setNString(1, "%" + title + "%");
            prStm.setObject(2, status, Types.VARCHAR);
            prStm.setObject(3, status, Types.VARCHAR);
            prStm.setInt(4, (page - 1) * ListPaging.ROW_PER_PAGE);
            prStm.setInt(5, ListPaging.ROW_PER_PAGE);
            rs = prStm.executeQuery();
            while (rs.next()) {
                if (listArt.getTotalRow() == null) {
                    listArt.setTotalRow(rs.getInt("totalRow"));
                }
                ArticleDTO article = new ArticleDTO();
                article.setId(rs.getInt("id"));
                article.setTitle(rs.getNString("title"));
                article.setShortDescription(rs.getNString("shortDescription"));
                UserDTO user = new UserDTO();
                user.setFullname(rs.getNString("authorName"));
                article.setAuthor(user);
                article.setPostingDate(rs.getDate("postingDate"));
                article.setStatus(rs.getString("status"));
                listArt.add(article);
            }
        } finally {
            closeConn();
        }
        return listArt;
    }

    public ArticleDTO getArticle(int id) throws NamingException, SQLException {
        ArticleDTO article = null;
        try {
            conn = MyConnection.getMyConnection();
            String sqlSelect = "SELECT a.title , a.content , a.shortDescription , u.fullname, a.postingDate , a.status FROM dbo.articles AS a JOIN dbo.users AS u ON u.email = a.author WHERE a.id = ?";
            prStm = conn.prepareStatement(sqlSelect);
            prStm.setInt(1, id);
            rs = prStm.executeQuery();
            if (rs.next()) {
                article = new ArticleDTO();
                article.setTitle(rs.getNString("title"));
                article.setContent(rs.getNString("content"));
                article.setShortDescription(rs.getNString("shortDescription"));
                UserDTO user = new UserDTO();
                user.setFullname(rs.getNString("fullname"));
                article.setAuthor(user);
                article.setPostingDate(rs.getDate("postingDate"));
                article.setStatus(rs.getString("status"));
            }

        } finally {
            closeConn();
        }
        return article;
    }
}
