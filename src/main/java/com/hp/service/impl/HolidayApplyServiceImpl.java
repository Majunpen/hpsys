package com.hp.service.impl;


import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.dto.HolidayApplyQuery;
import com.hp.enums.HolidayApplyStatus;
import com.hp.pojo.Account;
import com.hp.pojo.HolidayApply;
import com.hp.mapper.HolidayApplyMapper;
import com.hp.service.IAccountService;
import com.hp.service.IDeptService;
import com.hp.service.IEmployeeService;
import com.hp.service.IHolidayApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hp.wf.WorkflowConstant;
import com.utils.AssertUtil;
import com.utils.PageResultUtils;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mjp
 * @since 2023-02-01
 */
@Service
public class HolidayApplyServiceImpl extends ServiceImpl<HolidayApplyMapper, HolidayApply> implements IHolidayApplyService {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDeptService deptService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void saveHolidayApply(HolidayApply holidayApply) {
        //表单的基本非空验证
        checkParams(holidayApply.getTitle(),holidayApply.getHolidayType(),holidayApply.getReason());

        //判断请假时间，并设置请假天数
        setHolidays(holidayApply); //引入传递和值传递的区别

        //获取当前登录的用户名
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findAccountByUserName(name);

        holidayApply.setAccountId(account.getId());
        holidayApply.setSubmitTime(new Date());
        //设置请假单状态
        holidayApply.setStatus(HolidayApplyStatus.SUBMIT.getValue());//设置请假单状态
        holidayApply.setIsValid(1);

        AssertUtil.isTrue(!(this.save(holidayApply)),"记录添加成功");

        //完成请假单，启动请假流程实例
        String businessKey = holidayApply.getId().toString(); //以请假单的编号，作为业务key

        Map<String,Object> map =new HashMap<>();
        map.put("user",name);

        ProcessInstance processInstance = runtimeService.
                startProcessInstanceByKey(WorkflowConstant.EMPLOYEE_HOLIDAY_PROCESS_DEFINITION_KEY, businessKey, map);
        holidayApply.setProcessInstanceId(processInstance.getProcessInstanceId());//流程实例id
        //更新请假单记录
        AssertUtil.isTrue(!(this.updateById(holidayApply)),"请假失败");

        //完成当前填写请假单任务
        //查询当前请假人的请假任务
        Task task = taskService.createTaskQuery().processDefinitionKey(WorkflowConstant.EMPLOYEE_HOLIDAY_PROCESS_DEFINITION_KEY)
                .taskAssignee(name).list().get(0);
        if (null !=task && null !=this.getById(task.getBusinessKey())){
            //完成当前任务，设置下一个流程的代理人
            map.put("manager",employeeService.queryDeptMangerByUserName(account.getEmpId()).getEmpName());
            taskService.complete(task.getId(),map);
        }

    }

    @Override // 查询当事人的请假单
    public Map<String, Object> queryMyHolidayApply(HolidayApplyQuery holidayApplyQuery) {
        IPage<HolidayApply> page = new Page<>(holidayApplyQuery.getPage(),holidayApplyQuery.getLimit());
        QueryWrapper<HolidayApply> queryWrpper = new QueryWrapper<>();//查询条件
        String accountName = SecurityContextHolder.getContext().getAuthentication().getName();
        Account account = accountService.findAccountByUserName(accountName);
        //一定是根据当前登录的人，查询该用户的请假单
        queryWrpper.eq("account_id",account.getId());
        //根据标题查询
        if(StringUtils.isNotBlank(holidayApplyQuery.getTitle())){
            queryWrpper.like("title",holidayApplyQuery.getTitle());
        }
        //根据请假状态查询
        if(StringUtils.isNotBlank(holidayApplyQuery.getStatus())){
            queryWrpper.like("status",holidayApplyQuery.getStatus());
        }
        //根据开始时间查询
        if(StringUtils.isNotBlank(holidayApplyQuery.getStartTime())){
            queryWrpper.ge("start_time",holidayApplyQuery.getStartTime());
        }
        //根据结束时间查询
        if(StringUtils.isNotBlank(holidayApplyQuery.getStartTime())){
            queryWrpper.le("end_time",holidayApplyQuery.getEndTime());
        }
        queryWrpper.eq("is_valid",1);
        page = this.baseMapper.selectPage(page,queryWrpper);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());

    }

    private void setHolidays(HolidayApply holidayApply) {
        AssertUtil.isTrue(StringUtils.isBlank(holidayApply.getTime()),"请选择请假时间");
        //将时间切割成开始和结束时间
        String[] times = holidayApply.getTime().split(" - ");
        //请假开始时间
        DateTime startTime = DateUtil.parse(times[0].trim());
        //请假结束时间
        DateTime endTime = DateUtil.parse(times[1].trim());
        //结束时间必须大于开始时间
        AssertUtil.isTrue(DateUtil.compare(endTime,startTime)<=0,"结束时间必须大于开始时间");

        //根据时间计算一共请假多少天 不满一天按一天计算
        long days = DateUtil.between(endTime, startTime, DateUnit.DAY);
        holidayApply.setStartTime(startTime);
        holidayApply.setEndTime(endTime);
        holidayApply.setDays((int) days+1);



    }

    private void checkParams(String title, Integer holidayType, String reason) {
        AssertUtil.isTrue(StringUtils.isBlank(title),"请设置请假标题");
        AssertUtil.isTrue(null ==holidayType,"请选择请假类型");
        AssertUtil.isTrue(StringUtils.isBlank(reason),"请输入请假原因");

    }
}
