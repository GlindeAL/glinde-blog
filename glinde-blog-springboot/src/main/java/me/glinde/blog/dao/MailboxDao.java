package me.glinde.blog.dao;

import me.glinde.blog.entity.Mailbox;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailboxDao {
    int deleteByPrimaryKey(Integer mid);

    int insert(Mailbox record);

    int insertSelective(Mailbox record);

    Mailbox selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Mailbox record);

    int updateByPrimaryKey(Mailbox record);

    List<Mailbox> getAllMails(int pageStart,int pageSize,String query);

    int getMailCounts(String query);

    List<Mailbox> getAdminMails(int pageStart,int pageSize,String query);

    int getAdminMailCounts(String query);
}