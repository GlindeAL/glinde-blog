package me.glinde.blog.controller;

import com.alibaba.fastjson.JSON;
import me.glinde.blog.entity.Blog;
import me.glinde.blog.entity.Mailbox;
import me.glinde.blog.pojo.QueryInfo;
import me.glinde.blog.service.HomeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Resource
    HomeService homeService;

    @GetMapping("/blogs")
    public String getAllBlogs(QueryInfo queryInfo){
        HashMap<String,Object> res = new HashMap<>();
        int total = homeService.getBlogCounts(queryInfo.getQuery());
        List<Blog> blogs = homeService.getAllBlogs(queryInfo);
        if(blogs!=null){
            res.put("flag","success");
        }else{
            res.put("flag","error");
        }
        res.put("total",total);
        res.put("blogs",blogs);
        String res_json = JSON.toJSONString(res);
        return res_json;
    }

    @GetMapping("/blog/{bid}")
    public String getSingleBlog(@PathVariable Integer bid){
        HashMap<String,Object> res = new HashMap<>();
        Blog blog = homeService.getSingleBlog(bid);
        if(blog!=null){
            res.put("flag","success");
        }else{
            res.put("flag","error");
        }
        res.put("blog",blog);
        String res_json = JSON.toJSONString(res);
        return res_json;
    }

    @GetMapping("/mailbox/get")
    public String getAllMails(QueryInfo queryInfo){
        HashMap<String,Object> res = new HashMap<>();
        int total = homeService.getMailCounts(queryInfo.getQuery());
        List<Mailbox> mails = homeService.getAllMails(queryInfo);
        res.put("total",total);
        res.put("mails",mails);
        if(mails!=null){
            res.put("flag","success");
        }else{
            res.put("flag","error");
        }
        String res_json = JSON.toJSONString(res);
        return res_json;
    }

    @PostMapping("/mailbox/submit")
    public String submitMail(@RequestBody Mailbox mail){
        HashMap<String,Object> res = new HashMap<>();
        String flag = homeService.submitMail(mail);
        res.put("flag",flag);
        String res_json = JSON.toJSONString(res);
        return res_json;
    }
}
