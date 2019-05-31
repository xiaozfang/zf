package com.xiao.scheduled.config;

public enum TaskEnum {
    TEST(0, "测试");
    private int taskId;
    private String taskName;

    private TaskEnum(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public String getTaskName() {
        return this.taskName;
    }
}
