package com.hp.controller;


import cn.hutool.crypto.SecureUtil;
import com.hp.dto.HolidayApplyQuery;
import com.hp.pojo.Account;
import com.hp.pojo.Employee;
import com.hp.pojo.HolidayApply;
import com.hp.pojo.HolidayApproval;
import com.hp.resp.RespBean;
import com.hp.service.IAccountService;
import com.hp.service.IEmployeeService;
import com.hp.service.IHolidayApplyService;
import com.hp.service.IHolidayApprovalService;
import com.hp.wf.WorkflowConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mjp
 * @since 2023-02-01
 */
@Controller
@RequestMapping("/holidayApply")
public class HolidayApplyController {
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IHolidayApprovalService holidayApprovalService;

    @Autowired
    private IHolidayApplyService holidayApplyService;
    /*进入到请假主页面*/
    @GetMapping("/index")
    public String index(){
        return "holiday/holiday";
    }

    /*进入到添加页面*/
    @GetMapping("/addHolidayPage")
    public String addHolidayPage(Model model){
        //获取当前登录信息
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findAccountByUserName(name);//获取当前用户对象
        Employee currUser = employeeService.getById(account.getEmpId());//
        model.addAttribute("currUser",currUser);

        //查寻部门领导
       Employee manager= employeeService.queryDeptMangerByUserName(account.getEmpId());
        System.out.println(account.getEmpId());
       model.addAttribute("manager",manager);

       //查询公司总经理
       Employee boss= employeeService.findBoos();
       model.addAttribute("boss",boss);

       //查询所有的人事
       List<Employee> hrs= employeeService.findAllHrs();
       model.addAttribute("hrs",hrs);
        return "holiday/add_holiday";
    }

    //添加请假记录
    @RequestMapping("save")
    @ResponseBody
    public RespBean saveHoliday(HolidayApply holidayApply){
        holidayApplyService.saveHolidayApply(holidayApply);
        return RespBean.success("添加成功");
    }

    /**
     * 查询请假单
     */
    @RequestMapping("/queryMyHolidayApply")
    @ResponseBody
    public Map<String,Object> queryMyHolidayApply(HolidayApplyQuery holidayApplyQuery){
        return holidayApplyService.queryMyHolidayApply(holidayApplyQuery);
    }

    /*进入到请假单详情*/
    @RequestMapping("/toHolidayInfoPage")
   public String toHolidayInfoPage(Integer id,Model model) {
        //根据id，查询请假单
        HolidayApply holidayApply = holidayApplyService.getById(id);
        //根据id查询任务记录
        model.addAttribute("holidayApply", holidayApply);
        //发起人
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findAccountByUserName(name);

        model.addAttribute("user", employeeService.getById(account.getEmpId()));
        //根据用户名查询部门领导
        Employee manager = employeeService.queryDeptMangerByUserName(account.getEmpId());
        model.addAttribute("manager", manager);
        //查询部门领导审批记录
        String procInsId = holidayApply.getProcessInstanceId();
       // Object holidayApprovalService;
        HolidayApproval managerCheck = holidayApprovalService.queryHolidayApprovalByProcIdAndUserNameAndTaskDefKey(
                procInsId,
                WorkflowConstant.EMPLOYEE_HOLIDAY_MANAGER_TASK_DEF_KEY,
                Arrays.asList(manager.getEmail())
        );
        if (null != managerCheck) {
            model.addAttribute("managerCheck", managerCheck);
        }
        //查询公司总经理
        Employee boss = employeeService.findBoss();
        model.addAttribute("boss", boss);
        HolidayApproval bossCheck = holidayApprovalService.queryHolidayApprovalByProcIdAndUserNameAndTaskDefKey(
                procInsId,
                WorkflowConstant.EMPLOYEE_HOLIDAY_BOSS_TASK_DEF_KEY,
                Arrays.asList(boss.getEmail())
        );
        if (null != bossCheck) {
            model.addAttribute("bossCheck", bossCheck);
        }
        //查询所有的行政
        List<Employee> hrs = employeeService.findAllHrs();
        model.addAttribute("hrs", hrs);
        //查询行政审批
        HolidayApproval hrCheck = holidayApprovalService
                .queryHolidayApprovalByProcIdAndUserNameAndTaskDefKey(
                        procInsId,
                        WorkflowConstant.EMPLOYEE_HOLIDAY_HR_TASK_DEF_KEY,
                        hrs.stream().map(e -> e.getEmail()).collect(Collectors.toList())
                );
        //查询行政审批记录
        if (null != hrCheck) {
            model.addAttribute("hrCheck", hrCheck);
        }
        return "holiday/info";

    }

}
