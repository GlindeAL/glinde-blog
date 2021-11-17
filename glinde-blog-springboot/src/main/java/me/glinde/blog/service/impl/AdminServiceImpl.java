package me.glinde.blog.service.impl;

import me.glinde.blog.dao.BlogDao;
import me.glinde.blog.dao.MailboxDao;
import me.glinde.blog.dao.UserDao;
import me.glinde.blog.entity.Blog;
import me.glinde.blog.entity.Mailbox;
import me.glinde.blog.entity.User;
import me.glinde.blog.pojo.QueryInfo;
import me.glinde.blog.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    BlogDao blogDao;
    @Resource
    UserDao userDao;
    @Resource
    MailboxDao mailboxDao;

    Random random = new Random();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean submitBlog(Blog blog) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd | HH:mm:ss");
        blog.setDate(format.format(date));
        blog.setState(1);
        int res = blogDao.insertSelective(blog);
        return res > 0;
    }

    @Override
    public boolean checkUser(User user) {
        User us = userDao.getUserByMessage(user.getUsername(),user.getPassword());
        if(us==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String uploadImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        fileName = random.nextInt(10000000) + fileName;
        try{
            file.transferTo(new File("/root/workplace/image/"+fileName));
        }catch (IOException exception){
            exception.printStackTrace();
            return "error";
        }
        return "/image/"+fileName;
    }

    @Override
    public List<Mailbox> getAdminMails(QueryInfo queryInfo){
        int pageStart = ((queryInfo.getPageNum()-1)*queryInfo.getPageSize());
        List<Mailbox> mails = mailboxDao.getAdminMails(pageStart,queryInfo.getPageSize(), queryInfo.getQuery());
        return mails;
    }

    @Override
    public boolean updateMailState(Integer mid, Integer state){
        Mailbox mailbox = new Mailbox();
        mailbox.setMid(mid);
        mailbox.setState(state);
        return mailboxDao.updateByPrimaryKeySelective(mailbox) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteMail(int mid){
        return mailboxDao.deleteByPrimaryKey(mid) > 0;
    }

    @Override
    public int getAdminMailCounts(String query){
        int pageCount = mailboxDao.getAdminMailCounts(query);
        return pageCount;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBlog(Blog blog){
        return blogDao.updateByPrimaryKey(blog) > 0;
    }

    @Override
    public List<Blog> getAdminBlogs(QueryInfo queryInfo){
        int pageStart = ((queryInfo.getPageNum()-1)*queryInfo.getPageSize());
        List<Blog> blogs = blogDao.getAdminBlogs(pageStart,queryInfo.getPageSize(), queryInfo.getQuery());
        return blogs;
    }

    @Override
    public int getAdminBlogCounts(String query){
        int pageCount = blogDao.getAdminBlogCounts(query);
        return pageCount;
    }

    @Override
    public boolean updateBlogState(Integer bid, Integer state){
        Blog blog = new Blog();
        blog.setBid(bid);
        blog.setState(state);
        return blogDao.updateByPrimaryKeySelective(blog) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBlog(Integer bid){
        return blogDao.deleteByPrimaryKey(bid) > 0;
    }
}
