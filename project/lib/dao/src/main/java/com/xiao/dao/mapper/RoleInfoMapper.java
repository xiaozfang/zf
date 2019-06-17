package com.xiao.dao.mapper;

import com.xiao.common.response.BaseListResponse;
import com.xiao.dao.entity.RoleInfo;
import com.xiao.domain.usercenter.request.RoleSearchRequest;
import com.xiao.domain.usercenter.response.RoleBaseInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleInfo record);

    int insertSelective(RoleInfo record);

    RoleInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleInfo record);

    int updateByPrimaryKey(RoleInfo record);
    //——————————————————————————————————以下为新增方法————————————————————————————————————//

    BaseListResponse<RoleBaseInfo> selectRoleListByRequest(RoleSearchRequest request);

    @Update({
            "update role_info set status = #{status} where deleted = 0 and roleid = #{roleid}"
    })
    int updateRoleStatus(@Param("roleid") int roleid, @Param("status") int status);

}