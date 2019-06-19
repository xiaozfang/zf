package com.xiao.common.response;

import com.xiao.common.response.vo.IResponseVO;

public class BaseResponse implements IResponseVO {
    private static final Long serialVersionUID = 1L;

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
        if ("".equals(message) || message == null){
            this.message = code == 0 ? "请求失败" : "请求成功";
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BaseResponse success() {
        this.code = 1;
        this.message = "请求成功";
        return this;
    }

    public BaseResponse success(String msg) {
        this.code = 1;
        this.message = msg;
        return this;
    }

    public BaseResponse fail() {
        this.code = 0;
        this.message = "请求失败";
        return this;
    }

    public BaseResponse fail(String msg) {
        this.code = 0;
        this.message = msg;
        return this;
    }
}

