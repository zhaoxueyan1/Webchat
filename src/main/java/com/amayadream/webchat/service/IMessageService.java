package com.amayadream.webchat.service;

import com.amayadream.webchat.pojo.Log;
import com.amayadream.webchat.pojo.Message;

import java.util.List;

public interface IMessageService {
    List<Message> selectAll(int page, int pageSize);
    int selectCount(int pageSize);
    boolean insert(Message message);
}
