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
 * @since 2023-02-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_holiday_apply")
@ApiModel(value="HolidayApply对象", description="")
public class HolidayApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "申请用户id")
    private Integer accountId;

    @ApiModelProperty(value = "请假标题")
    private String title;

    @ApiModelProperty(value = "申请人姓名")
    private String userName;

    @ApiModelProperty(value = "请假原因")
    private String reason;

    @ApiModelProperty(value = "请假天数")
    private Integer days;

    @ApiModelProperty(value = "请假类型")
    private Integer holidayType;

    @ApiModelProperty(value = "请假提交时间")
    private Date submitTime;

    @ApiModelProperty(value = "请假备注信息")
    private String remark;

    @ApiModelProperty(value = "请假单状态")
    private Integer status;

    @ApiModelProperty(value = "流程审批状态")
    private Integer approvalStatus;

    @ApiModelProperty(value = "流程实例id")
    private String processInstanceId;

    @ApiModelProperty(value = "请假开始时间")
    private Date startTime;

    @ApiModelProperty(value = "请假结束时间")
    private Date endTime;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否有效")
    private Integer isValid;

    @ApiModelProperty(value = "请假时间段")
    @TableField(exist = false )
    private String time;


}
