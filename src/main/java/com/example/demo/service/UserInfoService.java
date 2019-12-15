package com.example.demo.service;

import com.example.demo.dao.domain.UserInfo;

import java.util.List;

public interface UserInfoService {
    UserInfo checkLogin(String username,String password);
    Boolean doRegister(String username,String password);
}
