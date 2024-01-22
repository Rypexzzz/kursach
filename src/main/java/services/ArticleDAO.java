package services;

import models.Article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
    DataBase db = new DataBase();

    public List<Article> getAll() {
        List<Article> articles = new ArrayList<>();

        try {
            ResultSet rs = db.select("SELECT a.*, u.username as authorString FROM articles a JOIN users u ON a.author=u.id ORDER BY a.id");
            if (rs == null) {
                return articles;
            }
            while (rs.next()) {
                articles.add(new Article(
                        rs.getInt("id"),
                        rs.getInt("author"),
                        rs.getString("title"),
                        rs.getString("text"),
                        rs.getDate("publication"),
                        rs.getString("authorString")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return articles;
    }

    public Article getByID(int id) {
        Article article = null;

        try {
            ResultSet rs = db.select("SELECT a.*, u.username as authorString FROM articles a JOIN users u ON a.author=u.id WHERE a.id=" + id);
            if (rs == null) {
                return article;
            }
            if (rs.next()) {
                article = new Article(
                        rs.getInt("id"),
                        rs.getInt("author"),
                        rs.getString("title"),
                        rs.getString("text"),
                        rs.getDate("publication"),
                        rs.getString("authorString")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return article;
    }

    public List<Article> getByAuthor(int author) {
        List<Article> articles = new ArrayList<>();

        try {
            ResultSet rs = db.select("SELECT * FROM articles WHERE author=" + author + " ORDER BY id");
            if (rs == null) {
                return articles;
            }
            while (rs.next()) {
                articles.add(new Article(
                        rs.getInt("id"),
                        rs.getInt("author"),
                        rs.getString("title"),
                        rs.getString("text"),
                        rs.getDate("publication")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return articles;
    }

    public boolean create(Article article) {
        String sql = "INSERT INTO articles(author, title, text) values("
                + article.getAuthor() + ", \'"
                + article.getTitle() + "\', \'"
                + article.getText() + "\')";

        return db.insert(sql);
    }

    public boolean edit(int id, Article article) {
        try {
            ResultSet rs = db.select("SELECT * FROM articles WHERE id=" + id);
            if (rs == null) {
                return false;
            }
            if (rs.next()) {
                rs.updateInt("author", article.getAuthor());
                rs.updateString("title", article.getTitle());
                rs.updateString("text", article.getText());
                rs.updateRow();
            } else throw new SQLException();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean delete(int id) {

        String sql = "DELETE FROM articles WHERE id = " + id;
        return db.delete(sql);
    }
}