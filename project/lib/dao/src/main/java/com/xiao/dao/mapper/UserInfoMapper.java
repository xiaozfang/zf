package com.xiao.dao.mapper;

import com.xiao.dao.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    //——————————————————————————————————以下为新增方法————————————————————————————————————//
    @Select({
            "select email from user_info limit 1"
    })
    String test(String userid);

    @Select({
            "select * from user_info where username = #{username} and password = #{password} and status = 1 and deleted = 0"
    })
    UserInfo login(@Param("username") String username, @Param("password") String password);

    @Select({
            "select * from user_info where userid =#{userid} and status = 1 and deleted = 0"
    })
    UserInfo selectUserInfoByUserId(@Param("userid") int userid);
}