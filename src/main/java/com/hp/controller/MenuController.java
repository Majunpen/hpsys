package com.hp.controller;



import com.hp.dto.TreeDto;
import com.hp.pojo.Menu;
import com.hp.resp.RespBean;
import com.hp.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mjp
 * @since 2023-01-13
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @RequestMapping("/queryAllMenus")
    @ResponseBody
    public List<TreeDto> queryAllMenus(Integer roleId){
        return menuService.queryAllMenus(roleId);
    }

    //进入到菜单管理首页
    @GetMapping("/index")
    public String index(){
        return "menu/index";
    }

    /**
     * 菜单列表查询
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> menuList(){
        return menuService.menuList();
    }
    //进入到菜单添加页面
    @RequestMapping("/addMenuPage")
    public String addMenuPage(Integer grade, Integer parentId, Model model){
        model.addAttribute("parentMenu",menuService.getById(parentId));
        model.addAttribute("parentId",parentId);
        model.addAttribute("grade",grade);
        return "menu/add";
    }

    /**
     * 添加菜单对象
     */
    @RequestMapping("/save")
    @ResponseBody
    public RespBean saveMenu(Menu menu){
        menuService.saveMenu(menu);
        return RespBean.success("记录添加成功");
    }

    //进入到修改页面
    @RequestMapping("/updateMenuPage")
    public String updateMenuPage(Integer id, Model model){
        //根据菜单id，查询要修改的菜单对象
        Menu menu = menuService.getById(id);
        model.addAttribute("menu",menu);
        model.addAttribute("parentMenu",menuService.getById(menu.getParentId()));
        return "menu/update";
    }

    /**
     * 修改菜单对象
     */
    @RequestMapping("/update")
    @ResponseBody
    public RespBean updateMenu(Menu menu){
        menuService.updateMenu(menu);
        return RespBean.success("记录修改成功");
    }

    /**
     * 删除菜单对象
     */
    @RequestMapping("/delete")
    @ResponseBody
    public RespBean deleteMenu(Integer id){
        menuService.deleteMenu(id);
        return RespBean.success("记录删除成功");
    }


}
