package com.example.demo.dao.mapper;

import com.example.demo.dao.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserInfoMapper {

    UserInfo findByUsername(String username);
    void doRegister(String username,String password);
}

