package models;

public class Comment {
    private int id;
    private int article;
    private int author;
    private String text;

    private String authorString;

    public Comment(int article, int author, String text) {
        this.article = article;
        this.author = author;
        this.text = text;
    }

    public Comment(int id, int article, int author, String text) {
        this(article, author, text);
        this.id = id;
    }

    public Comment(int id, int article, int author, String text, String authorString) {
        this(id, article, author, text);
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthorString() {
        return authorString;
    }

    public void setAuthorString(String authorString) {
        this.authorString = authorString;
    }
}
