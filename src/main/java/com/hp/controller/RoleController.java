package com.hp.controller;


import com.hp.pojo.Role;
import com.hp.resp.RespBean;
import com.hp.service.IAccountRoleService;
import com.hp.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mjp
 * @since 2023-01-12
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;
    @GetMapping("/index")
    public String index(){
        return "role/index";
    }

    //用户角色分配
    @RequestMapping("/assignUserPage")
    public String assignUserPage(Integer roleId, Model model) {
        model.addAttribute("roleId",roleId);
        return "role/assign";
    }

    //查询所有的信息
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(){
        return roleService.findList();
    }

    //进入到添加修改页面
    //用户角色分配
    @RequestMapping("/addOrUpdateRolePage")
    public String addOrUpdateRole() {
        return "role/add_update";
    }
    //进入到添加修改页面
    //用户角色分配
    @RequestMapping("/toSelectUserPage")
    public String toSelectUserPage(Integer roleId, Model model) {
        model.addAttribute("roleId",roleId);
        return "role/assign_select_user";
    }




    //添加代码
    @RequestMapping("/save")
    @ResponseBody
    public RespBean save(Role role){
        return roleService.saveRole(role);
    }

    //角色修改
    @RequestMapping("/update")
    @ResponseBody
    public RespBean update(Role role){
        return roleService.updateRole(role);
    }
    @Autowired
    private IAccountRoleService accountRoleService;
    //给用户分配角色
    @RequestMapping("/assignRoleToUser")
    @ResponseBody
    public RespBean assignRoleToUser(Integer roleId,Integer eId){
        return accountRoleService.saveRoleToUser(roleId,eId);
    }
    /**
     * 取消角色分配
     */
    @RequestMapping("/cancelRoleToUser")
    @ResponseBody
    public RespBean cancelRoleToUser(Integer roleId, Integer accountId){
        roleService.cancelRoleToUser(roleId,accountId);
        return RespBean.success("用户取消成功");
    }

    //权限展示页面
    @RequestMapping("/toAddGrantPage")
    public String toAddGrantPage(Integer roleId, Model model){
        model.addAttribute("roleId",roleId);
        return "role/grant";
    }



    /**
     * 添加权限 （建立角色和菜单之间的关系）
     */
    @RequestMapping("/addGrant")
    @ResponseBody
    public RespBean addGrant(Integer[] mids,Integer roleId){
        roleService.addGrant(mids,roleId);
        return RespBean.success("权限添加成功");
    }
}
