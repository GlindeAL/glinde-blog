package me.glinde.blog.entity;

public class Blog {
    private Integer bid;

    private String title;

    private String description;

    private String content;

    private String date;

    private Integer author;

    private Integer state;

    public Blog(Integer bid, String title, String description, String content, String date, Integer author, Integer state) {
        this.bid = bid;
        this.title = title;
        this.description = description;
        this.content = content;
        this.date = date;
        this.author = author;
        this.state = state;
    }

    public Blog() {
        super();
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}