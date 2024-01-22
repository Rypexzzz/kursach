package models;

public class Coauthor {
    private int id;
    private int article;
    private int author;
    private String authorString;

    public Coauthor(int article, int author) {
        this.article = article;
        this.author = author;
    }

    public Coauthor(int id, int article, int author) {
        this(article, author);
        this.id = id;
    }

    public Coauthor(int id, int article, int author, String authorString) {
        this(id, article, author);
        this.authorString = authorString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticle() {
        return article;
    }

    public void setArticle(int article) {
        this.article = article;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getAuthorString() {
        return authorString;
    }

    public void setAuthorString(String authorString) {
        this.authorString = authorString;
    }
}
