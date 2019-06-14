package com.xiao.zuul.pojo;

import lombok.Data;

import java.util.List;

@Data
public class LoginUser {
    private int userid;
    private String username;
    private List<Integer> roles;
}
