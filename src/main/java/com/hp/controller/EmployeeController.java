package com.hp.controller;


import com.hp.pojo.Employee;
import com.hp.req.EmployeeQuery;
import com.hp.resp.RespBean;
import com.hp.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mjp
 * @since 2023-01-11
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @GetMapping("/list")
    @ResponseBody
    /*@PreAuthorize("hasauthority('601002')")*/
    public Map<String ,Object> employeeList(EmployeeQuery employeeQuery){
        return employeeService.employeeList(employeeQuery);
    }

    /*进入到员工列表首页 employee/index*/
    @GetMapping("/index")
    public String index(){
        return "employee/index";
    }

    @RequestMapping("/save")
    @ResponseBody
   /* @PreAuthorize("hasauthority('601001')")*/
    public RespBean saveEmployee(Employee employee){
        employeeService.saveEmployee(employee);
        return RespBean.success("员工添加成功");
    }

    //进入到添加页面
    @GetMapping("/addEmployeePage")
    public String addEmployeePage(){
        return "employee/add";
    }

    /**
     * 用户信息修改
     */
    @RequestMapping("/update")
    @ResponseBody
   /* @PreAuthorize("hasauthority('601003')")*/
    public RespBean updateEmployee(Employee employee){
        employeeService.updateEmployee(employee);
        return RespBean.success("记录更新成功");
    }

    /**
     * 删除用户
     */
    @RequestMapping("/delete")
    @ResponseBody
    /*@PreAuthorize("hasauthority('601004')")*/
    public RespBean deleteEmployee(Integer id){
        employeeService.deleteEmployee(id);
        return RespBean.success("记录删除成功");
    }
}
