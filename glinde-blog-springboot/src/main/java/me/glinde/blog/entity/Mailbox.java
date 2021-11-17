package me.glinde.blog.entity;

public class Mailbox {
    private Integer mid;

    private String content;

    private String date;

    private Integer state;

    public Mailbox(Integer mid, String content, String date, Integer state) {
        this.mid = mid;
        this.content = content;
        this.date = date;
        this.state = state;
    }

    public Mailbox() {
        super();
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}