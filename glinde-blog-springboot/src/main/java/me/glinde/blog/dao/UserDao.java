package me.glinde.blog.dao;

import me.glinde.blog.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getUserByMessage(@Param("username") String username,@Param("password") String password);

}