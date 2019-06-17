package com.xiao.common.response;


import java.util.List;

public class BaseListResponse<T> extends BaseResponse {
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
