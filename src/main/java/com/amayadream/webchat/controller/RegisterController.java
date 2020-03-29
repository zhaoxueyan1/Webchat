package com.amayadream.webchat.controller;
import com.amayadream.webchat.dao.IUserDao;
import com.amayadream.webchat.pojo.User;
import com.amayadream.webchat.service.ILogService;
import com.amayadream.webchat.service.IUserService;
import com.amayadream.webchat.serviceImpl.UserServiceImpl;
import com.amayadream.webchat.utils.CommonDate;
import com.amayadream.webchat.utils.LogUtil;
import com.amayadream.webchat.utils.NetUtil;
import com.amayadream.webchat.utils.WordDefined;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

@Controller
@RequestMapping(value = "/user")
public class RegisterController {
    @Resource
    private IUserService userService;

    @Resource
    private ILogService logService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(String userid, String password1, HttpSession session, RedirectAttributes attributes,
                           WordDefined defined, CommonDate date, LogUtil logUtil, NetUtil netUtil, HttpServletRequest request) {
        User user = new User(userid,password1);
        User us = userService.selectUserByUserid(userid);
        //System.out.println("register");
        if(us==null){
            boolean flag = userService.insert(user);
            logService.insert(logUtil.setLog(userid, date.getTime24(), defined.LOG_TYPE_UPDATE, defined.LOG_DETAIL_UPDATE_PROFILE, netUtil.getIpAddress(request)));
            attributes.addFlashAttribute("message", "注册成功!");
        }else{
            attributes.addFlashAttribute("error", "注册失败!");
        }
        return "redirect:/user/login";
    }

}
