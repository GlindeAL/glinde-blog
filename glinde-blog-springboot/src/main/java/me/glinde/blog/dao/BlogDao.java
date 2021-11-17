package me.glinde.blog.dao;

import me.glinde.blog.entity.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogDao {
    int deleteByPrimaryKey(Integer bid);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer bid);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    List<Blog> getAllBlogs(int pageStart,int pageSize,String query);

    int getBlogCounts(String query);

    List<Blog> getAdminBlogs(int pageStart,int pageSize,String query);

    int getAdminBlogCounts(String query);
}