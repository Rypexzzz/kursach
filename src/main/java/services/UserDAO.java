package services;

import models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    DataBase db = new DataBase();

    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try {
            ResultSet rs = db.select("SELECT * FROM users");
            if (rs == null) {
                return users;
            }
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    public User getByID(int id) {
        User user = null;

        try {
            ResultSet rs = db.select("SELECT * FROM users WHERE id=" + id);
            if (rs == null) {
                return user;
            }
            if (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    public User getByUsername(String username) {
        User user = null;

        try {
            ResultSet rs = db.select("SELECT * FROM users WHERE username=\'" + username + "\'");
            if (rs == null) {
                return user;
            }
            if (rs.next()) {
                user = new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    public Integer create(User user) {
        Integer id = null;

        if (getByUsername(user.getUsername()) == null) {
            String sql = "INSERT INTO users(username, password) values(\'"
                    + user.getUsername() + "\', \'"
                    + user.getPassword() + "\')";

            if (db.insert(sql) && (user = getByUsername(user.getUsername())) != null) {
                id = user.getId();
            }
        }

        return id;
    }

    public boolean edit(int id, User user) {
        try {
            ResultSet rs = db.select("SELECT username FROM users WHERE id!=" + id + " AND username=\'" + user.getUsername() + "\'");
            if (rs.next()) {
                throw new SQLException("Username is already taken");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NullPointerException e) {}

        try {
            ResultSet rs = db.select("SELECT * FROM users WHERE id=" + id);
            if (rs == null) {
                return false;
            }
            if (rs.next()) {
                rs.updateString("username", user.getUsername());
                rs.updateRow();
            } else throw new SQLException();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM users WHERE id = " + id;
        return db.delete(sql);
    }
}