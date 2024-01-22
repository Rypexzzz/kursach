package services;

import models.Source;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SourceDAO {
    DataBase db = new DataBase();

    public List<Source> getAll() {
        List<Source> sources = new ArrayList<>();

        try {
            ResultSet rs = db.select("SELECT s.*, u.username FROM sources s JOIN users u ON s.author=u.id ORDER BY s.id");
            if (rs == null) {
                return sources;
            }
            while (rs.next()) {
                sources.add(new Source(
                        rs.getInt("id"),
                        rs.getInt("article"),
                        rs.getString("link")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return sources;
    }

    public Source getByID(int id) {
        Source source = null;

        try {
            ResultSet rs = db.select("SELECT s.*, u.username FROM sources s JOIN users u ON s.author=u.id WHERE s.id=" + id);
            if (rs == null) {
                return source;
            }
            if (rs.next()) {
                source = new Source(
                        rs.getInt("id"),
                        rs.getInt("article"),
                        rs.getString("link")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return source;
    }

    public List<Source> getByArticle(int article) {
        List<Source> sources = new ArrayList<>();

        try {
            ResultSet rs = db.select("SELECT * FROM sources WHERE article=" + article + " ORDER BY id");
            if (rs == null) {
                return sources;
            }
            while (rs.next()) {
                sources.add(new Source(
                        rs.getInt("id"),
                        rs.getInt("article"),
                        rs.getString("link")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return sources;
    }

    public boolean create(Source source) {
        String sql = "INSERT INTO sources(article, link) values("
                + source.getArticle() + ", \'"
                + source.getLink() + "\')";

        return db.insert(sql);
    }

    public boolean edit(int id, Source source) {
        try {
            ResultSet rs = db.select("SELECT * FROM sources WHERE id=" + id);
            if (rs == null) {
                return false;
            }
            if (rs.next()) {
                rs.updateInt("article", source.getArticle());
                rs.updateString("link", source.getLink());
                rs.updateRow();
            } else throw new SQLException();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM sources WHERE id = " + id;
        return db.delete(sql);
    }
}