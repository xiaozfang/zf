package com.xiao.domain.usercenter.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("菜单基本信息")
public class MenuBaseInfo {
    /**
     * 菜单id
     */
    @ApiModelProperty("菜单id  创建不传")
    private Integer id;


    /**
     * 菜单名字
     */
    @ApiModelProperty("菜单名字")
    private String menuname;

    /**
     * 菜单等级
     */
    @ApiModelProperty("菜单等级")
    private Integer level;

    /**
     * 父菜单id
     */
    @ApiModelProperty("父菜单id")
    private Integer parentid;

    /**
     * 跳转url
     */
    @ApiModelProperty("跳转url")
    private String url;

}
