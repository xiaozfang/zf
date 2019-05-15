package com.xiao.domain.common;

import java.io.Serializable;

public class ResponseDataBase<T> extends ResponseBase implements Serializable {

    private static final Long serialVersionUID = 1L;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
