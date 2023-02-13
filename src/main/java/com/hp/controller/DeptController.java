package com.hp.controller;


import com.hp.dto.DeptDto;
import com.hp.pojo.Dept;
import com.hp.resp.RespBean;
import com.hp.service.IDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mjp
 * @since 2023-01-05
 */
@Controller
@RequestMapping("/dept")
public class  DeptController {

    @Autowired
    private IDeptService deptService;

    //进入到部门页面
    @GetMapping("/index")
    public String toDept() {
        return "dept/index";
    }

    //进入到添加页面
    @GetMapping("/addDeptPage")
    public String addOrUpdateDeptPage(Integer level,Integer parentId,Map<String ,Object> map){
        //根据父id，查询父部门（上级部门）
        map.put("parentDept",deptService.getById(parentId));
        map.put("parentId",parentId);
        map.put("level",level);
        return "dept/add";
    }
    //添加部门
    @RequestMapping("/save")
    @ResponseBody
    public RespBean saveDept(Dept dept){
        deptService.saveDept(dept);
        return RespBean.success("部门添加成功");
    }

    //查询所有部门
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> deptList(){
        return deptService.deptList();
    }

    //更新部门表单页面
    @GetMapping("/updateDeptPage")
    public String updateDeptPage(Integer id, Model model){
        Dept dept = deptService.getById(id); //根据id获取到要修改的对象
        model.addAttribute("dept",dept);
        model.addAttribute("parentDept",deptService.getById(dept.getParentId()));
        return "dept/update";

    }

    //更新部门
    @RequestMapping("/update")
    @ResponseBody
    public RespBean update(Dept dept){
        deptService.updateDept(dept);
        return RespBean.success("修改成功");
    }

    //删除部门
    @RequestMapping("/delete")
    @ResponseBody
    public RespBean delete( Integer id){
        deptService.deleteDept(id);
        return RespBean.success("删除成功");
    }

    /*   //查询所有的部门
    @RequestMapping("/findAllDept")
    @ResponseBody
    public Map<String,Object> findAll(){
        return deptService.deptList();
    }*/

    //查询所有的部门
    @RequestMapping("/findAllDept")
    @ResponseBody
    public List<DeptDto> findAll(){
        return deptService.findAllDept();
    }


}
