package me.glinde.blog.service;

import me.glinde.blog.entity.Blog;
import me.glinde.blog.entity.Mailbox;
import me.glinde.blog.pojo.QueryInfo;

import java.util.List;

public interface HomeService {

    List<Blog> getAllBlogs(QueryInfo queryInfo);
    Blog getSingleBlog(int bid);
    List<Mailbox> getAllMails(QueryInfo queryInfo);
    String submitMail(Mailbox mail);
    int getBlogCounts(String query);
    int getMailCounts(String query);

}
