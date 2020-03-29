package com.amayadream.webchat.controller;

import com.amayadream.webchat.pojo.Log;
import com.amayadream.webchat.pojo.Message;
import com.amayadream.webchat.pojo.User;
import com.amayadream.webchat.service.ILogService;
import com.amayadream.webchat.service.IMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping(value = "")
public class MessageController {

    @Resource
    private IMessageService messageService;


    @RequestMapping(value = "/message")
    public ModelAndView selectAll(@RequestParam(defaultValue = "1") int page) {
        int pageSize = 5;
        ModelAndView view = new ModelAndView("message");
        List<Message> list = messageService.selectAll(page, pageSize);
        int count = messageService.selectCount(pageSize);
        view.addObject("list", list);
        view.addObject("count", count);
        return view;
    }
//    @RequestMapping(value = "/message/insert", method = RequestMethod.POST)
//    public String register(String userid, String password1, HttpSession session, RedirectAttributes attributes,
//                           WordDefined defined, CommonDate date, LogUtil logUtil, NetUtil netUtil, HttpServletRequest request) {
//        User user = new User(userid,password1);
//        User us = userService.selectUserByUserid(userid);
//        //System.out.println("register");
//        if(us.getUserid().equals("")){
//            boolean flag = userService.insert(user);
//            logService.insert(logUtil.setLog(userid, date.getTime24(), defined.LOG_TYPE_UPDATE, defined.LOG_DETAIL_UPDATE_PROFILE, netUtil.getIpAddress(request)));
//            attributes.addFlashAttribute("message", "注册成功!");
//        }else{
//            attributes.addFlashAttribute("error", "注册失败!");
//        }
//        return "redirect:/user/login";
//    }

}
