package com.hp.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: MJP
 * @Date: 2023/1/13 - 01 - 13 - 21:10
 * @Description: com.hp.req
 * @version: 1.0
 */
@Data
public class AccountRoleQuery extends BaseQuery implements Serializable {
    private String empName;
    private String empNum;
    private String roleId;

}
