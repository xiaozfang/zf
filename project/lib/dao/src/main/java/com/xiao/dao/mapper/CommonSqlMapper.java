package com.xiao.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommonSqlMapper<T> {
    @Select({
            "${sql}"
    })
    T selectOne(@Param("sql") String sql);

    @Select({
            "${sql}"
    })
    List<T> selectList(@Param("sql") String sql);
}
