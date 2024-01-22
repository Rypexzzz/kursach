package services;

import models.Coauthor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoauthorDAO {
    DataBase db = new DataBase();

    public List<Coauthor> getAll() {
        List<Coauthor> coauthors = new ArrayList<>();

        try {
            ResultSet rs = db.select("SELECT c.*, u.username FROM coauthors c JOIN users u ON c.author=u.id ORDER BY c.id");
            if (rs == null) {
                return coauthors;
            }
            while (rs.next()) {
                coauthors.add(new Coauthor(
                        rs.getInt("id"),
                        rs.getInt("article"),
                        rs.getInt("author"),
                        rs.getString("authorString")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return coauthors;
    }

    public Coauthor getByID(int id) {
        Coauthor coauthor = null;

        try {
            ResultSet rs = db.select("SELECT c.*, u.username FROM coauthors c JOIN users u ON c.author=u.id WHERE c.id=" + id);
            if (rs == null) {
                return coauthor;
            }
            if (rs.next()) {
                coauthor = new Coauthor(
                        rs.getInt("id"),
                        rs.getInt("article"),
                        rs.getInt("author"),
                        rs.getString("authorString")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return coauthor;
    }

    public List<Coauthor> getByArticle(int article) {
        List<Coauthor> coauthors = new ArrayList<>();

        try {
            ResultSet rs = db.select("SELECT c.*, u.username as authorString FROM coauthors c JOIN users u ON c.author=u.id WHERE c.article=" + article + " ORDER BY c.id");
            if (rs == null) {
                return coauthors;
            }
            while (rs.next()) {
                coauthors.add(new Coauthor(
                        rs.getInt("id"),
                        rs.getInt("article"),
                        rs.getInt("author"),
                        rs.getString("authorString")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return coauthors;
    }

    public boolean create(Coauthor coauthor) {
        String sql = "INSERT INTO coauthors(article, author) values("
                + coauthor.getArticle() + ", "
                + coauthor.getAuthor() + ")";

        return db.insert(sql);
    }

    public boolean edit(int id, Coauthor coauthor) {
        try {
            ResultSet rs = db.select("SELECT * FROM coauthors WHERE id=" + id);
            if (rs == null) {
                return false;
            }
            if (rs.next()) {
                rs.updateInt("article", coauthor.getArticle());
                rs.updateInt("author", coauthor.getAuthor());
                rs.updateRow();
            } else throw new SQLException();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM coauthors WHERE id = " + id;
        return db.delete(sql);
    }
}