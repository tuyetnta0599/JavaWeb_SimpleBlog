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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tuyetnta.dtos.RoleDTO;
import tuyetnta.dtos.UserDTO;
import tuyetnta.utils.MyConnection;

/**
 *
 * @author tuyet
 */
public class UserDAO implements Serializable {

    private Connection conn = null;
    private PreparedStatement prStm = null;
    private ResultSet rs = null;

    private void closeConn() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (prStm != null) {
            prStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public UserDTO checkLogin(String email, String password) throws NamingException, SQLException {
        UserDTO user = null;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "SELECT u.email, u.fullname, r.name AS roleName FROM dbo.users AS u JOIN dbo.roles AS r ON r.id = u.idRole WHERE u.email = ? AND u.password = ?";
            prStm = conn.prepareStatement(sql);
            prStm.setString(1, email);
            prStm.setString(2, password);
            rs = prStm.executeQuery();
            if (rs.next()) {
                user = new UserDTO();
                user.setEmail(rs.getString("email"));
                user.setFullname(rs.getNString("fullname"));
                user.setRole(new RoleDTO(rs.getString("roleName")));
            }
        } finally {
            closeConn();
        }
        return user;
    }

    public boolean add(UserDTO user) throws SQLException, NamingException {
        boolean check = false;
        try {
            conn = MyConnection.getMyConnection();
            String sql = "INSERT INTO dbo.users ( email , password , fullname , idRole , status ) VALUES (?, ?, ?, ?, ?)";
            prStm = conn.prepareStatement(sql);
            prStm.setString(1, user.getEmail());
            prStm.setString(2, user.getPassword());
            prStm.setString(3, user.getFullname());
            prStm.setInt(4, user.getRole().getId());
            prStm.setString(5, user.getStatus());
            check = prStm.executeUpdate() > 0;
        } finally {
            closeConn();
        }
        return check;
    }

    public List<UserDTO> getAllUser() throws SQLException, NamingException {
        List<UserDTO> listUser = new ArrayList<>();
        try {
            conn = MyConnection.getMyConnection();
            String sql = "SELECT u.email , u.password , u.fullname , r.name as roleName FROM dbo.users AS u JOIN dbo.roles AS r ON r.id = u.idRole";
            prStm = conn.prepareStatement(sql);
            rs = prStm.executeQuery();
            while (rs.next()) {
                UserDTO user = new UserDTO();
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setFullname(rs.getNString("fullname"));
                user.setRole(new RoleDTO(rs.getString("roleName")));
                listUser.add(user);
            }
        } finally {
            closeConn();
        }
        return listUser;
    }
}
