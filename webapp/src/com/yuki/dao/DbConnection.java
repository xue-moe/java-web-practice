package com.yuki.dao;
import java.sql.*;

public class DbConnection {
    private static String url = "jdbc:mysql://localhost:3306/hrmanagement?characterEncoding=utf-8&useSSL=false";
    private static String user = "root";
    private static String pass = "root";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
       Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void getClose(java.sql.Connection conn, PreparedStatement pst, ResultSet rst) {
        try {
            if (rst != null)
                rst.close();
            if (pst != null)
                pst.close();
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
