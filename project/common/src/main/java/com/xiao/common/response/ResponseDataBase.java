package com.xiao.common.response;

public class ResponseDataBase<T> extends ResponseBase {

    private static final Long serialVersionUID = 1L;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
