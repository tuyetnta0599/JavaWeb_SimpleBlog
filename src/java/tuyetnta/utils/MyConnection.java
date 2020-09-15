/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuyetnta.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author tuyet
 */
public class MyConnection implements Serializable{
    public static Connection getMyConnection() throws NamingException, SQLException{
        Context currContext = new InitialContext();
        Context tomContext = (Context) currContext.lookup("java:comp/env");
        DataSource ds = (DataSource) tomContext.lookup("LAB3");
        Connection conn = ds.getConnection();
        return conn;
    }
}
