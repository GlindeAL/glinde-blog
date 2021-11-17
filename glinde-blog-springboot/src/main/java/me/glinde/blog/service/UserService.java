package me.glinde.blog.service;

import me.glinde.blog.entity.User;

public interface UserService {

    User getUserByMessage(String username, String password);

}
