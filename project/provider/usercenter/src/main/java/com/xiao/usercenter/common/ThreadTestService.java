package com.xiao.usercenter.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.Executor;

@Slf4j
@Component
public class ThreadTestService {


    @Resource(name = "CommonPool")
    private Executor common;

    private static Random random = new Random();

    @Async("CommonPool")
    public void doTask() {
        log.info("开始做任务一");
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(random.nextInt(10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
    }

    @Async("CommonPool")
    public void doTaskTwo() throws Exception {
        log.info("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(random.nextInt(10000));
        long end = System.currentTimeMillis();
        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
    }

    @Async("CommonPool")
    public void doTaskThree(){
        try {
            long start = System.currentTimeMillis();
            log.info("开始做任务三");
            Thread.sleep(100);
            long end = System.currentTimeMillis();
            log.info("完成任务三，耗时：" + (end - start) + "毫秒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Async
    public void doTaskFour(){
        log.info("方法2：");
        for (int i = 0; i < 50; i ++){
            common.execute(new Runnable() {
                @Override
                public void run() {
                    doTaskThree();
                }
            });
        }
    }

    public void test(){

    }
}
