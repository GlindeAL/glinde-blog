package me.glinde.blog.vo;

import me.glinde.blog.entity.Blog;
import me.glinde.blog.entity.User;

public class EditForm {

    private Blog blog;
    private User user;

    public EditForm() {}

    public EditForm(Blog blog, User user) {
        this.blog = blog;
        this.user = user;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
