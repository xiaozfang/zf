package com.xiao.dao.mapper;

import com.xiao.dao.entity.UserRoleInfo;
import com.xiao.domain.usercenter.response.RoleBaseInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
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
            "select roleid, rolename from user_role_info where deleted = 0 and status = 1 and userid = #{userId}"
    })
    List<RoleBaseInfo> selectRolesByUserId(@Param("userId") Integer userId);

    @Insert({
            "insert into user_role_info (userid, roleid, status) values(#{userId}, #{roleId}, 1)"
    })
    int insertUserRoleInfo(@Param("userId") Integer userId, @Param("roleId") int roleId);

    @Update({
            "update user_role_info set status = ${status}",
            "where deleted = 0 and userid =#{userId} and roleid = #{roleId}"
    })
    int updateUserRoleStatus(@Param("userId") Integer userId, @Param("roleId") int roleId, @Param("status") int status);

    @Update({
            "update user_role_info set status = #{status} where deleted = 0 and roleid = #{roleId}"
    })
    int updateUserRoleStatusByRoleId(@Param("roleId") int roleId, @Param("status") int status);
}