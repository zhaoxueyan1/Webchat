package com.amayadream.webchat.dao;
import com.amayadream.webchat.pojo.Log;
import com.amayadream.webchat.pojo.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "messageDao")
public interface IMessageDao {
    List<Message> selectAll(@Param("offset") int offset, @Param("limit") int limit);

    Message selectCount();

    boolean insert(Message message);

//    List<Message> selectMessageById(@Param("id") String id, @Param("offset") int offset, @Param("limit") int limit);

//    boolean delete(String id);

//    boolean deleteAll();
}
