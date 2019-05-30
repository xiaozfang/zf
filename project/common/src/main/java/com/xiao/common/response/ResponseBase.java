package com.xiao.common.response;

import com.xiao.common.response.vo.IResponseVO;
import lombok.Data;

@Data
public class ResponseBase implements IResponseVO {
    private static final Long serialVersionUID = 1L;

    private int code;
    private String message;

    public ResponseBase SUCCESS() {
        this.code = 1;
        this.message = "请求成功";
        return this;
    }

    public ResponseBase SUCCESS(String msg) {
        this.code = 1;
        this.message = msg;
        return this;
    }

    public ResponseBase FAIL() {
        this.code = 0;
        this.message = "请求失败";
        return this;
    }

    public ResponseBase FAIL(String msg) {
        this.code = 0;
        this.message = msg;
        return this;
    }
}

