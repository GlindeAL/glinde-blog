package me.glinde.blog.controller;

import com.alibaba.fastjson.JSON;
import me.glinde.blog.entity.User;
import me.glinde.blog.service.UserService;
import me.glinde.blog.util.JWTUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user){
        HashMap<String,Object> res = new HashMap<>();
        User us = userService.getUserByMessage(user.getUsername(), user.getPassword());
        if(us==null){
            res.put("flag","error");
        }else{
            res.put("uid",us.getUid());
            res.put("username",us.getUsername());
            res.put("flag","success");
        }
        try{
            HashMap<String,String> payload = new HashMap<>();
            payload.put("uid",us.getUid().toString());
            payload.put("username",us.getUsername());
            String token = JWTUtils.getToken(payload);
            res.put("token",token);
        }catch (Exception e){
            res.remove("uid");
            res.remove("username");
            res.remove("flag");
            res.put("flag","error");
        }
        String res_json = JSON.toJSONString(res);
        return res_json;
    }

    @GetMapping("/check")
    public String check(){
        HashMap<String,Object> res = new HashMap<>();
        res.put("flag","success");
        String res_json = JSON.toJSONString(res);
        return res_json;
    }
}
