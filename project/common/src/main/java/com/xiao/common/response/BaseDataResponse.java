package com.xiao.common.response;

public class BaseDataResponse<T> extends BaseResponse {

    private static final Long serialVersionUID = 1L;

    private T data;

    public T getData() {
        return data;
    }

    public BaseDataResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public BaseDataResponse<T> success() {
        this.setCode(1);
        this.setMessage("操作成功");
        return this;
    }

    public BaseDataResponse<T> fail() {
        this.setCode(0);
        this.setMessage("操作失败");
        return this;
    }


    public BaseDataResponse<T> success(String msg) {
        this.setCode(1);
        this.setMessage(msg);
        return this;
    }

    public BaseDataResponse<T> fail(String msg) {
        this.setCode(0);
        this.setMessage(msg);
        return this;
    }
}
