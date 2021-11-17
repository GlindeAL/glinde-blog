package me.glinde.blog.entity;

public class User {
    private Integer uid;

    private String username;

    private String password;

    private Integer status;

    private Integer state;

    public User(Integer uid, String username, String password, Integer status, Integer state) {
        this.uid = uid;
        this.username = username;
        this.password = password;
        this.status = status;
        this.state = state;
    }

    public User() {
        super();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}