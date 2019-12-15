package com.example.demo.util;

import java.io.Serializable;

/**
 * state:返回值,一般前端先判断返回值再输出 message
 * message:给前端的信息
 * data:给前端的json数据
 * @param <T> 操作结果中包含的数据的类型
 */
public class ResponseResult<T> implements Serializable {
    private Integer state;
    private String message;
    private T data;

    public ResponseResult() {
        super();
    }

    public ResponseResult(Integer state) {
        super();
        this.state = state;
    }

    public ResponseResult(Integer state, T data) {
        super();
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
