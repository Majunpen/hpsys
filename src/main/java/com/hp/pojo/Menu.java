package com.hp.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author mjp
 * @since 2023-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_menu")
@ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "自增主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "资源名称")
    private String menuName;

    @ApiModelProperty(value = "模块样式")
    private String menuStyle;

    @ApiModelProperty(value = "地址")
    private String url;

    @ApiModelProperty(value = "父级菜单id")
    private Integer parentId;

    @ApiModelProperty(value = "父级菜单权限")
    private String parentOptValue;

    @ApiModelProperty(value = "等级")
    private Integer grade;

    @ApiModelProperty(value = "权限值")
    private String optValue;

    @ApiModelProperty(value = "排序")
    private Integer orders;

    @ApiModelProperty(value = "是否有效")
    private Integer isValid;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "上级名称")
    @TableField(exist = false) //这是数据库中没有的字段
    private String parentName;

}
