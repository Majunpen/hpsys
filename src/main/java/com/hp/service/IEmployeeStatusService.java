package com.hp.service;

import com.hp.pojo.EmployeeStatus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mjp
 * @since 2023-01-10
 */
public interface IEmployeeStatusService extends IService<EmployeeStatus> {

    List<EmployeeStatus> queryAllEmployeeStatus();
}
