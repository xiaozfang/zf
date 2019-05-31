package com.xiao.scheduled.task;

import com.xiao.dao.entity.TaskConfig;
import com.xiao.dao.mapper.TaskConfigMapper;
import com.xiao.scheduled.config.TaskEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 使用方法：
 * （1）在数据库的OrderTask表中新增一条定时任务的记录
 * （2）新增Runnable类的定时任务执行方法
 * （3）在configureTasks类中调用（2）中的方法，同时使用getTrigger()获取数据库中该任务的触发时间
 */
@Slf4j
@Component
public class SchedulerTask implements SchedulingConfigurer {
    @Autowired
    private TaskConfigMapper taskConfigMapper;

    //保证所有任务是并行执行
    @Bean
    public TaskScheduler poolScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("poolScheduler");
        scheduler.setThreadNamePrefix("task-");
        scheduler.setPoolSize(15);
        return scheduler;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(test(), getTrigger(TaskEnum.TEST.getTaskId()));
    }

    /**
     * 从数据库中获取触发时间
     */
    private Trigger getTrigger(int taskId) {
        return triggerContext -> {
            TaskConfig task = taskConfigMapper.selectByTaskId(taskId);
            if (task != null) {
                log.info("任务编码:{}, 任务名称:{}, 执行状态:{}, 执行时间: {}", taskId, task.getTaskname(), task.getStatus(), task.getCron());
                // 触发器
                CronTrigger trigger = new CronTrigger(task.getCron());
                return trigger.nextExecutionTime(triggerContext);
            } else {
                log.info("定时任务初始化失败，任务编码：{},请检查数据库中的任务配置。", taskId);
                return null;
            }
        };
    }


    /**
     * 测试
     */
    private Runnable test() {
        return () -> {
            log.info("====>{}定时任务执行开始", TaskEnum.TEST.getTaskName());
            long start = System.currentTimeMillis();
            TaskConfig task = taskConfigMapper.selectByTaskId(TaskEnum.TEST.getTaskId());
            if (task.getStatus() == 1) {
                log.info(LocalDateTime.now() + "执行中......");
            } else {
                log.info("====>{}定时任务已关闭，不执行", task.getTaskname());
            }
            long end = System.currentTimeMillis();
            log.info("====>{}定时任务执行结束，耗时: {}ms", TaskEnum.TEST.getTaskName(), end - start);
        };
    }
}