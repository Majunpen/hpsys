package com.hp.controller;


import com.hp.pojo.Employee;
import com.hp.pojo.EmployeeStatus;
import com.hp.resp.RespBean;
import com.hp.service.IEmployeeStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mjp
 * @since 2023-01-10
 */
@Controller
@RequestMapping("/employeeStatus")
public class EmployeeStatusController {
   @Autowired
   private IEmployeeStatusService employeeStatusService;

   @RequestMapping("/index")
   public String index(){
       return "employee_status/index";
   }

   //查询职位状态
   @RequestMapping("/queryAllEmployeeStatus")
   @ResponseBody
   public List<EmployeeStatus> queryAllEmployeeStatus(){
      return employeeStatusService.queryAllEmployeeStatus();
   }




}
