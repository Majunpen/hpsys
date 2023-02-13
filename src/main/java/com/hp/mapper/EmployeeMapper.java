package com.hp.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hp.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hp.req.EmployeeQuery;
import com.hp.vo.EmployeeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mjp
 * @since 2023-01-11
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
    IPage<EmployeeVo> getEmployeeList(IPage page, @Param("employeeQuery")EmployeeQuery employeeQuery);

    /*根据empid插叙用户部门经理*/
    Employee queryDeptMangerByUserName (Integer id);

    List<Employee> findEmp(String titleName);
}
