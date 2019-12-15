package com.example.demo.dao.domain;

public class UserInfo {
    //主键id
    private String id;
    private String password;
    //获取id
    public String getId(){
        return id;
    }
    //获取密码
    public String getPassword(){
        return password;
    }
    //设置id
    public void setId(String id){
        this.id=id;
    }
    //设置密码
    public void setPassword(String password){
       this.password=password;
    }
    @Override
    public String toString(){
        return " "+id+" "+password;
    }
}
