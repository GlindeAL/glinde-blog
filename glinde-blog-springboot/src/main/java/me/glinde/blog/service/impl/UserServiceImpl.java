package me.glinde.blog.service.impl;

import me.glinde.blog.entity.User;
import me.glinde.blog.dao.UserDao;
import me.glinde.blog.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    public User getUserByMessage(String username,String password){
        User user = userDao.getUserByMessage(username,password);

        return user;
    }
}
