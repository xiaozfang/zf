package com.xiao.usercenter;

import com.xiao.usercenter.common.ThreadTestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ThreadPoolConfigTest {

    @Autowired
    ThreadTestService service;

    @Test
    public void userCenterCommonPool() throws Exception {
//        service.doTask();
//        service.doTaskTwo();
//        service.doTaskThree();
        service.doTaskFour();
//        Thread.sleep(1000);
//        System.out.println(Thread.currentThread().getName());
//        Thread.currentThread().join();
    }
}