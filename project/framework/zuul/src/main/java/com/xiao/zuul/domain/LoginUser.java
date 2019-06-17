package com.xiao.zuul.domain;

import lombok.Data;

import java.util.List;

@Data
public class LoginUser {
    private int userid;
    private String username;
    private List<Integer> roles;
}
