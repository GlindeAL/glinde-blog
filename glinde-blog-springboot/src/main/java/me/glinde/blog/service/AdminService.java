package me.glinde.blog.service;

import me.glinde.blog.entity.Blog;
import me.glinde.blog.entity.Mailbox;
import me.glinde.blog.entity.User;
import me.glinde.blog.pojo.QueryInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminService {

    boolean submitBlog(Blog blog);
    boolean checkUser(User user);
    String uploadImage(MultipartFile file);
    List<Mailbox> getAdminMails(QueryInfo queryInfo);
    boolean updateMailState(Integer mid,Integer state);
    boolean deleteMail(int mid);
    int getAdminMailCounts(String query);
    boolean updateBlog(Blog blog);
    List<Blog> getAdminBlogs(QueryInfo queryInfo);
    int getAdminBlogCounts(String query);
    boolean updateBlogState(Integer bid,Integer state);
    boolean deleteBlog(Integer bid);
}
