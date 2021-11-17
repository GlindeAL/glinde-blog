package me.glinde.blog.service.impl;

import me.glinde.blog.dao.BlogDao;
import me.glinde.blog.dao.MailboxDao;
import me.glinde.blog.entity.Blog;
import me.glinde.blog.entity.Mailbox;
import me.glinde.blog.pojo.QueryInfo;
import me.glinde.blog.service.HomeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class HomeServiceImpl implements HomeService {
    @Resource
    BlogDao blogDao;
    @Resource
    MailboxDao mailboxDao;

    @Override
    public List<Blog> getAllBlogs(QueryInfo queryInfo){
        int pageStart = (queryInfo.getPageNum()-1)*queryInfo.getPageSize();
        List<Blog> blogList = blogDao.getAllBlogs(pageStart,queryInfo.getPageSize(),queryInfo.getQuery());
        return blogList;
    }

    @Override
    public int getBlogCounts(String query){
        int pageCount = blogDao.getBlogCounts(query);
        return pageCount;
    }

    @Override
    public Blog getSingleBlog(int bid){
        return blogDao.selectByPrimaryKey(bid);
    }

    @Override
    public List<Mailbox> getAllMails(QueryInfo queryInfo){
        int pageStart = ((queryInfo.getPageNum()-1)*queryInfo.getPageSize());
        List<Mailbox> mailList = mailboxDao.getAllMails(pageStart,queryInfo.getPageSize(), queryInfo.getQuery());
        return mailList;
    }

    @Override
    public int getMailCounts(String query){
        int pageCount = mailboxDao.getMailCounts(query);
        return pageCount;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String submitMail(Mailbox mail){
        if(mail.getState()!=0&&mail.getState()!=2){
            mail.setState(0);
        }
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd | HH:mm:ss");
        mail.setDate(format.format(date));
        int res = mailboxDao.insertSelective(mail);
        if(res>0){
            return "success";
        }
        return "error";
    }
}
