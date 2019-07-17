package com.xiao.dao.mapper;

import com.xiao.dao.entity.TaskConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskConfig record);

    int insertSelective(TaskConfig record);

    TaskConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TaskConfig record);

    int updateByPrimaryKey(TaskConfig record);

    @Select({
            "select * from task_config where taskid = #{taskId} and deleted = 0 limit 1"
    })
    TaskConfig selectByTaskId(@Param("taskId") int taskId);
}