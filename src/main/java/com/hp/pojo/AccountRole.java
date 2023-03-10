package com.hp.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("t_account_role")
@ApiModel(value="AccountRole对象", description="")
public class AccountRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "账户id")
    private Integer accountId;

    @ApiModelProperty(value = "角色 id")
    private Integer roleId;


}
