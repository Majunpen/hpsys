package com.hp.service;

import com.hp.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hp.req.EmployeeQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mjp
 * @since 2023-01-11
 */
public interface IEmployeeService extends IService<Employee> {
    /**
     * 查询员工信息列表
     * @param employeeQuery
     * @return
     */

    public Map<String ,Object> employeeList(EmployeeQuery employeeQuery);

    void saveEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(Integer id);

    Employee queryDeptMangerByUserName(Integer empId);

   Employee findBoos();

    List<Employee> findAllHrs();
}
