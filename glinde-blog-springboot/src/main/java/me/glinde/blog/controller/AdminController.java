package me.glinde.blog.controller;

import com.alibaba.fastjson.JSON;
import me.glinde.blog.entity.Blog;
import me.glinde.blog.entity.Mailbox;
import me.glinde.blog.pojo.QueryInfo;
import me.glinde.blog.service.AdminService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    AdminService adminService;

    @PostMapping("/edit")
    public String submitBlog(@RequestBody Blog blog){
        HashMap<String,Object> res = new HashMap<>();
        if(adminService.submitBlog(blog)){
            res.put("flag","success");
        }else{
            res.put("flag","error");
        }
        String res_json = JSON.toJSONString(res);
        return res_json;
    }

    @PostMapping("/uploadimg")
    public String uploadImg(@RequestParam("image") MultipartFile file){
        HashMap<String,Object> res = new HashMap<>();
        String filePath = adminService.uploadImage(file);
        if(!filePath.equals("error")){
            res.put("url",filePath);
            res.put("flag","success");
        }else{
            res.put("flag","error");
        }
        return JSON.toJSONString(res);
    }

    @PostMapping("/test")
    public String test(){
        return "访问成功";
    }

    @GetMapping("/mailbox/get")
    public String getAdminMails(QueryInfo queryInfo){
        HashMap<String,Object> res = new HashMap<>();
        int total = adminService.getAdminMailCounts(queryInfo.getQuery());
        List<Mailbox> mails = adminService.getAdminMails(queryInfo);
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

    @PostMapping("/mailbox/state/{mid}/{state}")
    public String updateMailState(@PathVariable Integer mid,@PathVariable Integer state){
        HashMap<String,Object> res = new HashMap<>();
        if(adminService.updateMailState(mid,state)){
            res.put("flag","success");
        }else{
            res.put("flag","error");
        }
        String res_json = JSON.toJSONString(res);
        return res_json;
    }

    @DeleteMapping("/mailbox/delete/{mid}")
    public String deleteMail(@PathVariable Integer mid){
        HashMap<String,Object> res = new HashMap<>();
        if(adminService.deleteMail(mid)){
            res.put("flag","success");
        }else{
            res.put("flag","error");
        }
        String res_json = JSON.toJSONString(res);
        return res_json;
    }

    @PostMapping("/blog/update")
    public String updateBlog(@RequestBody Blog blog){
        HashMap<String,Object> res = new HashMap<>();
        if(adminService.updateBlog(blog)){
            res.put("flag","success");
        }else{
            res.put("flag","error");
        }
        String res_json = JSON.toJSONString(res);
        return res_json;
    }

    @GetMapping("/blog/get")
    public String getAdminBlog(QueryInfo queryInfo){
        HashMap<String,Object> res = new HashMap<>();
        int total = adminService.getAdminBlogCounts(queryInfo.getQuery());
        List<Blog> blogs = adminService.getAdminBlogs(queryInfo);
        res.put("total",total);
        res.put("blogs",blogs);
        if(blogs!=null){
            res.put("flag","success");
        }else{
            res.put("flag","error");
        }
        String res_json = JSON.toJSONString(res);
        return res_json;
    }

    @PostMapping("/blog/state/{bid}/{state}")
    public String updateBlogState(@PathVariable Integer bid,@PathVariable Integer state){
        HashMap<String,Object> res = new HashMap<>();
        if(adminService.updateBlogState(bid,state)){
            res.put("flag","success");
        }else{
            res.put("flag","error");
        }
        String res_json = JSON.toJSONString(res);
        return res_json;
    }

    @DeleteMapping("/blog/delete/{bid}")
    public String deleteBlog(@PathVariable Integer bid){
        HashMap<String,Object> res = new HashMap<>();
        if(adminService.deleteBlog(bid)){
            res.put("flag","success");
        }else{
            res.put("flag","error");
        }
        String res_json = JSON.toJSONString(res);
        return res_json;
    }
}
