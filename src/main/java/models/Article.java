package models;

import java.sql.Date;

public class Article {
    private int id;
    private int author;
    private String title;
    private String text;
    private Date publication;
    private String authorString;

    public Article(int id, int author, String title, String text, Date date) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.text = text;
        this.publication = date;
    }
    public Article(int author, String title, String text) {
        this.author = author;
        this.title = title;
        this.text = text;
    }

    public Article(int id, int author, String title, String text, Date date, String authorString) {
        this(id, author, title, text, date);
        this.authorString = authorString;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPublication() {
        return publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }

    public String getAuthorString() {
        return authorString;
    }

    public void setAuthorString(String authorString) {
        this.authorString = authorString;
    }
}
