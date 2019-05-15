package com.xiao.domain.common;


import java.io.Serializable;
import java.util.List;

public class ResponseListBase<T> extends ResponseBase implements Serializable {
    private static final Long serialVersionUID = 1L;
    private List<T> data;

    private Integer totalCount;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}
