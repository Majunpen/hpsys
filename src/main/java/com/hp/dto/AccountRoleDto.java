package com.hp.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: MJP
 * @Date: 2023/1/13 - 01 - 13 - 21:22
 * @Description: com.hp.dto
 * @version: 1.0
 */
@Data
public class AccountRoleDto implements Serializable {
    private Integer aid;//账户id
    private String userName;//用户名
    private String empName;//员工姓名
    private String empNum;//员工编号
    private String detpNum;//部门编号
    private String title;//职位
}
