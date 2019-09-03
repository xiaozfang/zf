package com.xiao.common.response;


import java.util.List;

public class BaseListResponse<T> extends BaseResponse {
    private static final Long serialVersionUID = 1L;
    private List<T> data;

    private Integer totalCount;

    public List<T> getData() {
        return data;
    }

    public BaseListResponse<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public BaseListResponse<T> setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public BaseListResponse<T> success() {
        this.setCode(1);
        this.setMessage("操作成功");
        return this;
    }

    public BaseListResponse<T> fail() {
        this.setCode(0);
        this.setMessage("操作失败");
        return this;
    }


    public BaseListResponse<T> success(String msg) {
        this.setCode(1);
        this.setMessage(msg);
        return this;
    }

    public BaseListResponse<T> fail(String msg) {
        this.setCode(0);
        this.setMessage(msg);
        return this;
    }
}
