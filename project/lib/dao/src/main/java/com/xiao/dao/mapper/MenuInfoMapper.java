package com.xiao.dao.mapper;

import com.xiao.dao.entity.MenuInfo;
import com.xiao.domain.usercenter.response.MenuBaseInfo;
import com.xiao.domain.usercenter.request.MenuSearchRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuInfo record);

    int insertSelective(MenuInfo record);

    MenuInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuInfo record);

    int updateByPrimaryKey(MenuInfo record);

    //——————————————————————————————————以下为新增方法————————————————————————————————————//

    /**
     * 根据条件查询菜单列表
     */
    List<MenuBaseInfo> list(@Param("request") MenuSearchRequest request);
}