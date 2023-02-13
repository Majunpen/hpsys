package com.hp.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.pojo.Account;
import com.hp.pojo.Employee;
import com.hp.mapper.EmployeeMapper;
import com.hp.req.EmployeeQuery;
import com.hp.service.IAccountService;
import com.hp.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hp.vo.EmployeeVo;
import com.utils.AssertUtil;
import com.utils.PageResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mjp
 * @since 2023-01-11
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IAccountService accountService;
    @Override
    public Map<String, Object> employeeList(EmployeeQuery employeeQuery) {
        IPage<EmployeeVo> page = new Page<>(employeeQuery.getPage(),employeeQuery.getLimit()) ;
        page=this.baseMapper.getEmployeeList(page,employeeQuery);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());
    }

    //添加员工
    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)//碰到任何异常事回滚
    public void saveEmployee(Employee employee) {
        //基本信息验证
        checkParam(employee.getEmpName(),employee.getMobile(),employee.getEmail());
        //要求邮箱一定是唯一的
         Employee temp=  this.findEmployeeByEmail(employee.getEmail());
         AssertUtil.isTrue(null !=temp,"该邮箱已经被绑定");

         //获取到员工的工号
        String empNum = this.generateEmpNum();
        employee.setEmpNum(empNum);
        employee.setCreateTime(new Date());
        employee.setUpdateTime(new Date());
        employee.setFormalStatus("1");
        AssertUtil.isTrue(!(this.save(employee)),"记录添加失败");

        //创建账号对象
        Account account = new Account();
        account.setEmpId(employee.getId());
        account.setPassword(passwordEncoder.encode("123"));//新用户的默认密码是123
        account.setUserName(employee.getEmail());//用enail作为用户名
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());
        AssertUtil.isTrue(!(accountService.save(account)),"记录添加失败");

    }

    @Override //修改用户信息
    public void updateEmployee(Employee employee) {
        //查询要修改的的用户
        Employee temp = this.getOne(new QueryWrapper<Employee>()
                .eq("id", employee.getId())
                .eq("status", 1));
        AssertUtil.isTrue(null ==temp,"待更新的记录不存在");
        //校验属性
        checkParam(employee.getEmpName(),employee.getMobile(),employee.getEmail());
        temp =this.findEmployeeByEmail(employee.getEmail());
        //修改的email也不能和已经存在的Email重复
        AssertUtil.isTrue(null !=temp && !(temp.getId().equals(employee.getId())),"该邮箱有已经被绑定");

        employee.setUpdateTime(new Date());
        AssertUtil.isTrue(this.updateById(employee),"记录更新失败");

        //更新账户
        Account account = accountService.getOne(new QueryWrapper<Account>()
                .eq("emp_id", employee.getId()));
        if (!(account.getUsername().equals(employee.getEmail()))){
            account.setUserName(employee.getEmpName());
            account.setUpdateTime(new Date());
            AssertUtil.isTrue(!accountService.updateById(account),"记录更新失败");

        }

    }

    @Override
    public void deleteEmployee(Integer id) {
        //要删除的用户是否存在
        Employee employee = this.getOne(new QueryWrapper<Employee>()
                .eq("id", id).eq("status", 1));
        AssertUtil.isTrue(null == employee,"待删除的记录不存在");
        //删除是做逻辑删除，将status设置为0
        employee.setStatus(0);
        AssertUtil.isTrue(!(this.updateById(employee)),"记录删除失败!");

        //账户状态也需要修改
        Account account = this.accountService.findAccountByUserName(employee.getEmail());
        account.setStatus(0);//执行逻辑删除
        AssertUtil.isTrue(!(this.accountService.updateById(account)),"记录删除失败");
    }

    @Override
    public Employee queryDeptMangerByUserName(Integer empId) {
        return this.baseMapper.queryDeptMangerByUserName(empId);
    }

    @Override
    public Employee  findBoos() {
        return this.baseMapper.findEmp("老板").get(0);
    }

    @Override
    public List<Employee> findAllHrs() {
        return this.baseMapper.findEmp("人事");
    }

    private String generateEmpNum() {
        String empNum="000001";
        String maxEmpNum= this.baseMapper.selectOne(new QueryWrapper<Employee>()
                .select("max(emp_num) as empNum ")).getEmpNum();
        if(StringUtils.isNotEmpty(maxEmpNum)){ //查询到结果了
            Integer code= Integer.valueOf(maxEmpNum)+1;
            empNum=code.toString();
            Integer length=empNum.length();
            for (Integer integer = 6; integer >length; integer--) {
                empNum="0"+empNum;
            }
        }
        return empNum;
    }

    //根据邮箱查找用户对象--- 要求邮箱号是不能重复的
    private Employee findEmployeeByEmail(String email) {
        return this.baseMapper.selectOne(new QueryWrapper<Employee>()
                .eq("email",email)
                .eq("status",1));
    }
    //基本表单数据校验
    private void checkParam(String empName, String mobile, String email) {
        AssertUtil.isTrue(StringUtils.isBlank(empName),"请输入员工姓名");
        AssertUtil.isTrue(StringUtils.isBlank(mobile),"请输入手机号");
        AssertUtil.isTrue(StringUtils.isBlank(email),"请输入邮箱");

    }
}
