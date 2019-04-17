package com.xiao.project_1.service.impl;

import com.xiao.project_1.service.IRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegisterServiceImpl implements IRegisterService {
    @Override
    public String register(int a, String b) {
        log.info("====================");
        return "注册成功！";
    }
}
