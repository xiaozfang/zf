package com.xiao.dao.mapper;

import com.xiao.dao.entity.UserRoleInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleInfo record);

    int insertSelective(UserRoleInfo record);

    UserRoleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRoleInfo record);

    int updateByPrimaryKey(UserRoleInfo record);
    //——————————————————————————————————以下为新增方法————————————————————————————————————//
    @Select({
            "select roleid from user_role_info where deleted = 0 and status = 1 and userid = #{userid}"
    })
    List<Integer> selectRoleidsByUserid(@Param("userid") Integer userid);
}