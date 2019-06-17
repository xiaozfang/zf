package com.xiao.common.response;

public class BaseDataResponse<T> extends BaseResponse {

    private static final Long serialVersionUID = 1L;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
