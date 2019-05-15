package com.xiao.dao.mapper;

import com.xiao.dao.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectUserByUserid(String userid);

    void Update(User user);

    User deleteByUserid(String userid);
}