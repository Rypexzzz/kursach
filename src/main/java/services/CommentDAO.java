package services;

import models.Comment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {
    DataBase db = new DataBase();

    public List<Comment> getAll() {
        List<Comment> comments = new ArrayList<>();

        try {
            ResultSet rs = db.select("SELECT * FROM comments ORDER BY id");
            if (rs == null) {
                return comments;
            }
            while (rs.next()) {
                comments.add(new Comment(
                        rs.getInt("id"),
                        rs.getInt("article"),
                        rs.getInt("author"),
                        rs.getString("text")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return comments;
    }

    public Comment getByID(int id) {
        Comment comment = null;

        try {
            ResultSet rs = db.select("SELECT * FROM comments WHERE id=" + id);
            if (rs == null) {
                return comment;
            }
            if (rs.next()) {
                comment = new Comment(
                        rs.getInt("id"),
                        rs.getInt("article"),
                        rs.getInt("author"),
                        rs.getString("text")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return comment;
    }

    public List<Comment> getByArticle(int article) {
        List<Comment> comments = new ArrayList<>();

        try {
            ResultSet rs = db.select("SELECT c.*, u.username as authorString FROM comments c JOIN users u ON c.author=u.id WHERE c.article=" + article + " ORDER BY c.id");
            if (rs == null) {
                return comments;
            }
            while (rs.next()) {
                comments.add(new Comment(
                        rs.getInt("id"),
                        rs.getInt("article"),
                        rs.getInt("author"),
                        rs.getString("text"),
                        rs.getString("authorString")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return comments;
    }

    public boolean create(Comment comment) {
        String sql = "INSERT INTO comments(article, author, text) values("
                + comment.getArticle() + ", "
                + comment.getAuthor() + ", \'"
                + comment.getText() + "\')";

        return db.insert(sql);
    }

    public boolean edit(int id, Comment comment) {
        try {
            ResultSet rs = db.select("SELECT * FROM comments WHERE id=" + id);
            if (rs == null) {
                return false;
            }
            if (rs.next()) {
                rs.updateInt("article", comment.getArticle());
                rs.updateInt("author", comment.getAuthor());
                rs.updateString("text", comment.getText());
                rs.updateRow();
            } else throw new SQLException();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM comments WHERE id = " + id;
        return db.delete(sql);
    }
}