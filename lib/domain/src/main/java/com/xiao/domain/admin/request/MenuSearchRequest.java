package com.xiao.domain.admin.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("菜单查询参数")
public class MenuSearchRequest {

    @ApiModelProperty("菜单等级")
    private Integer level;

    @ApiModelProperty("菜单名称")
    private String menuname;
}
