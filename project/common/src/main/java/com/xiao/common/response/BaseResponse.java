package com.xiao.common.response;

import com.xiao.common.response.vo.IResponseVO;
import lombok.Data;

@Data
public class BaseResponse implements IResponseVO {
    private static final Long serialVersionUID = 1L;

    private int code;
    private String message;

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

