package com.hp.req;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: MJP
 * @Date: 2023/1/11 - 01 - 11 - 21:26
 * @Description: com.hp.req
 * @version: 1.0
 */
@Data
public class EmployeeQuery extends BaseQuery implements Serializable {
    private String empName;
    private Integer deptId;
    private String empNum;

}
