package com.example.demo.service.impl;

import com.example.demo.dao.domain.UserInfo;
import com.example.demo.dao.mapper.UserInfoMapper;
import com.example.demo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private  UserInfoMapper userInfoMapper;

    public UserInfo checkLogin(String username,String password){
        UserInfo userInfo=userInfoMapper.findByUsername(username);
        if(userInfo!=null&&userInfo.getPassword().equals(password)){
            return  userInfo;
        }
        return null;
    }

    public Boolean doRegister(String username,String password){
        UserInfo userInfo=userInfoMapper.findByUsername(username);
        if(userInfo==null){
            userInfoMapper.doRegister(username,password);
            return  true;
        }
        return false;
    }

}
