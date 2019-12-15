package com.example.demo.controller;

import com.example.demo.dao.domain.UserInfo;
import com.example.demo.service.UserInfoService;
import com.example.demo.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @Autowired
    private UserInfoService userInfoService;

    @ResponseBody
    @RequestMapping(value="/checkLogin")
    public ResponseResult<UserInfo> checkLogin(UserInfo userInfo, HttpServletRequest request, HttpSession session){
        String loginName=request.getParameter("username");
        String loginPass=request.getParameter("password");
        System.out.println(loginName+" "+loginPass);
        userInfo=userInfoService.checkLogin(loginName,loginPass);
        if(userInfo!=null){
            System.out.println("success");
            System.err.println(userInfo.getId());
            System.err.println(userInfo.getPassword());
            // 向Session中封装用户信息
            session.setAttribute("username", userInfo.getId());
            session.setAttribute("password", userInfo.getPassword());
            return new ResponseResult<>(200);
        }
        System.out.println("fail");
        return new ResponseResult<>(404);
    }

    @ResponseBody
    @RequestMapping(value="/checkRegister")
    public ResponseResult<UserInfo> checkRegister(Boolean userInfo, HttpServletRequest request){
        String loginName=request.getParameter("username");
        String loginPass=request.getParameter("password");
        System.out.println(loginName+" "+loginPass);
        userInfo=userInfoService.doRegister(loginName,loginPass);
        if(userInfo){
            System.out.println("success");
            return new ResponseResult<>(200);
        }
        System.out.println("fail");
        return new ResponseResult<>(404);
    }

    @ResponseBody
    @RequestMapping(value="/checkLogin1")
    public String checkChat(HttpSession session){
        System.out.println("hahaha");
        System.out.println(session.getAttribute("username"));
        if(session.getAttribute("username")==null) {

            return "404";
        }
        else {
            String temp=session.getAttribute("username").toString();
            return temp;
        }
    }

}
