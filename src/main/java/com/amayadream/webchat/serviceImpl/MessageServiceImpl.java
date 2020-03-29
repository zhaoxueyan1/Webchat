package com.amayadream.webchat.serviceImpl;

import com.amayadream.webchat.dao.ILogDao;
import com.amayadream.webchat.dao.IMessageDao;
import com.amayadream.webchat.pojo.Log;
import com.amayadream.webchat.pojo.Message;
import com.amayadream.webchat.service.IMessageService;
import com.amayadream.webchat.utils.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service(value = "messageService")
public class MessageServiceImpl implements IMessageService {

    @Resource private IMessageDao messageDao;
    @Resource private Message message;

    @Override
    public List<Message> selectAll(int page, int pageSize) {
        int start = 1;
        int end = pageSize;
        if(page != 1) {
            start = pageSize * (page - 1) + 1;
            end = pageSize * page;
        }
        return messageDao.selectAll(start, end);
    }

    @Override
    public int selectCount(int pageSize) {
        int pageCount = Integer.parseInt(messageDao.selectCount().getId());
        return pageCount % pageSize == 0 ? pageCount/pageSize : pageCount/pageSize + 1;
    }

    @Override
    public boolean insert(@org.jetbrains.annotations.NotNull Message message) {
        message.setId(StringUtil.getGuid());
        if(message.getTo().equals("")){
            message.setTo("all");
        }
        try {
            messageDao.insert(message);
        }catch (Exception ignored){
            return false;
        }
        return true;
    }
}
