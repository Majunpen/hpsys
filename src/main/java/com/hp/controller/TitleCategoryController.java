package com.hp.controller;


import com.hp.pojo.TitleCategory;
import com.hp.resp.RespBean;
import com.hp.service.ITitleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
 * @since 2023-01-10
 */
@Controller
@RequestMapping("/titleCategory")
public class TitleCategoryController {
    @Autowired
    private ITitleCategoryService titleCategoryService;

    @RequestMapping("/index")
    public String index(){
        return "title_category/index";
    }

    /**
     * 职位管理列表查询
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> titleCategoryList(){
        return titleCategoryService.titleCategoryList();
    }

    //添加页面
    @RequestMapping("/addTitleCategoryPage")
    public String addTitleCategoryPage(Integer parentId, Integer level, Model model){
    model.addAttribute("titleCategory",titleCategoryService.getById(parentId));
    model.addAttribute("parentId",parentId);
    model.addAttribute("level",level);
    return "title_category/add";
    }

    @RequestMapping("/save")
    @ResponseBody
    public RespBean saveTitleCategory(TitleCategory titleCategory){
        titleCategoryService.saveTitleCategory(titleCategory);
        return RespBean.success("职位添加成功");

    }

    //进入到修改页面
    @RequestMapping("/updateTitleCategoryPage")
    public String updateTitleCategoryPage(Integer id ,Model model){
        //根据id查询要修改的职位
        TitleCategory titleCategory = titleCategoryService.getById(id);
        //查询父职位
        TitleCategory  parentTitleCategory= titleCategoryService.getById(titleCategory.getParentId());
        model.addAttribute("titleCategory",titleCategory);
        model.addAttribute("parentTitleCategory",parentTitleCategory);
        return "title_category/update";
    }

    //进行修改职位
    @RequestMapping("/update")
    @ResponseBody
    public RespBean updateTitleCategory(TitleCategory titleCategory){
        titleCategoryService.updateTitleCategory(titleCategory);
        return RespBean.success("修改成功");
    }

    //删除部门
    @RequestMapping("/delete")
    @ResponseBody
    public RespBean delete(Integer id) {
        titleCategoryService.delete(id);
        return RespBean.success("删除成功");
    }

    /**
     * 查询所有的职位
     */
    @RequestMapping("/queryAllTitleCategories")
    @ResponseBody
    public List<TitleCategory> queryAllTitleCategories(){
        return titleCategoryService.queryAllTitleCategories();
    }

}