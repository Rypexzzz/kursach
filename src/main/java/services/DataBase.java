package services;

import java.sql.*;

public class DataBase {
    private Connection con = getConnect();

    private Connection getConnect() {
        final String url = "jdbc:postgresql://217.107.219.154:49307/bonch_2105457";
        final String user = "bonch_2105457";
        final String password = "vBUZSOTO7zg=";

        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return con;
    }

    public ResultSet select(String sql) {
        ResultSet rs = null;
        try {
            if (con != null) {
                rs = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE).executeQuery(sql);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rs;
    }

    public boolean insert(String sql){
        try {
            if (con != null) {
                int rowsAffected = con.createStatement().executeUpdate(sql);
                if (rowsAffected > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }

        return false;
    }

    public boolean delete(String sql) {
        try {
            if (con != null) {
                int rowsAffected = con.createStatement().executeUpdate(sql);
                if (rowsAffected > 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}