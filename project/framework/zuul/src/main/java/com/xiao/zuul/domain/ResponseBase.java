package com.xiao.zuul.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseBase implements Serializable {
    private static final Long serialVersionUID = 1L;

    private int code;
    private String message;

    public ResponseBase success() {
        this.code = 1;
        this.message = "请求成功";
        return this;
    }

    public ResponseBase success(String msg) {
        this.code = 1;
        this.message = msg;
        return this;
    }

    public ResponseBase fail() {
        this.code = 0;
        this.message = "请求失败";
        return this;
    }

    public ResponseBase fail(String msg) {
        this.code = 0;
        this.message = msg;
        return this;
    }
}

