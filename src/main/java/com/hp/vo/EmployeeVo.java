package com.hp.vo;

import com.hp.pojo.Employee;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: MJP
 * @Date: 2023/1/11 - 01 - 11 - 21:13
 * @Description: com.hp.vo
 * @version: 1.0
 */
//员工的视图类
@Data
public class EmployeeVo extends Employee implements Serializable {
    private String userName;
    private String deptName;
    private String titleName;
    private String empStatus;
}
