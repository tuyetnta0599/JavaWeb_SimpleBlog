/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuyetnta.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tuyetnta.dtos.ArticleCommentDTO;
import tuyetnta.dtos.UserDTO;
import tuyetnta.utils.MyConnection;

/**
 *
 * @author tuyet
 */
public class ArticleCommentDAO {

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

    public boolean addComment(int artID, String comment, String userEmailComment) throws SQLException, NamingException {
        boolean check = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "INSERT INTO dbo.articleComments ( idArticle , userComment , comment , commentDate ) VALUES ( ?, ?, ?, GETDATE())";
            prStm = conn.prepareStatement(sql);
            prStm.setInt(1, artID);
            prStm.setString(2, userEmailComment);
            prStm.setNString(3, comment);
            check = prStm.executeUpdate() > 0;
        } finally {
            closeConn();
        }
        return check;
    }

    public List<ArticleCommentDTO> getAllArticleComment(int idArt) throws SQLException, NamingException {
        List<ArticleCommentDTO> listComment = new ArrayList<>();
        try {
            conn = MyConnection.getMyConnection();
            String sql = "SELECT ac.id , u.fullname as name , ac.comment , ac.commentDate FROM dbo.articleComments AS ac JOIN dbo.users AS u ON u.email = ac.userComment WHERE ac.idArticle = ?";
            prStm = conn.prepareStatement(sql);
            prStm.setInt(1, idArt);
            rs = prStm.executeQuery();
            while (rs.next()) {
                ArticleCommentDTO comment = new ArticleCommentDTO();
                comment.setId(rs.getInt("id"));
                UserDTO user = new UserDTO();
                user.setFullname(rs.getNString("name"));
                comment.setUserComment(user);
                comment.setComment(rs.getNString("comment"));
                comment.setCommentDate(rs.getTimestamp("commentDate"));
                listComment.add(comment);
            }
        } finally {
            closeConn();
        }
        return listComment;
    }
}
