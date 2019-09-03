package com.xiao.dao.mapper;

import com.xiao.dao.entity.MsgLog;
import org.springframework.stereotype.Repository;

@Repository
public interface MsgLogMapper {
    int deleteByPrimaryKey(String msgId);

    int insert(MsgLog record);

    int insertSelective(MsgLog record);

    MsgLog selectByPrimaryKey(String msgId);

    int updateByPrimaryKeySelective(MsgLog record);

    int updateByPrimaryKeyWithBLOBs(MsgLog record);

    int updateByPrimaryKey(MsgLog record);
}