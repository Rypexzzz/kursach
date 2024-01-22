package models;

public class Source {
    private int id;
    private int article;
    private String link;

    public Source(int article, String link) {
        this.article = article;
        this.link = link;
    }

    public Source(int id, int article, String link) {
        this(article, link);
        this.id = id;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
