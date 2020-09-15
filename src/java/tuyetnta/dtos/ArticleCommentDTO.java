/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuyetnta.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author tuyet
 */
public class ArticleCommentDTO implements Serializable{
    private int id;
    private ArticleDTO article;
    private UserDTO userComment;
    private String comment;
    private Timestamp commentDate;

    public ArticleCommentDTO() {
    }

    public ArticleDTO getArticle() {
        return article;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO getUserComment() {
        return userComment;
    }

    public void setUserComment(UserDTO userComment) {
        this.userComment = userComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Timestamp commentDate) {
        this.commentDate = commentDate;
    }

}
