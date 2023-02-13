package com.hp.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

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
 * @since 2023-01-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_dept")
@ApiModel(value="Dept对象", description="")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "部门id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "部门编号")
    private String deptNum;

    @ApiModelProperty(value = "部门名称")
    private String deptName;

    @ApiModelProperty(value = "上级部门id")
    private Integer parentId;

    @ApiModelProperty(value = "部门层级")
    private Integer level;

    @ApiModelProperty(value = "部门经理")
    private Integer managerId;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "0：删除   1 : 正常使用   2：禁用")
    private Integer status;

    @ApiModelProperty(value = "上级部门名称")
    @TableField(exist = false)//表示该字段在数据库中是不存在的
    private String parentDeptName;

    @ApiModelProperty(value = "部门经理")
    @TableField(exist = false)//表示该字段在数据库中是不存在的
    private String manager;


}
