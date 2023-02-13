package com.hp.service;

import com.hp.pojo.TitleCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mjp
 * @since 2023-01-10
 */
public interface ITitleCategoryService extends IService<TitleCategory> {
    /**
     * 查询所有部门分类
     * @return
     */
    Map<String ,Object> titleCategoryList();

    void saveTitleCategory(TitleCategory titleCategory);

    void updateTitleCategory(TitleCategory titleCategory);

    void delete(Integer id);
    //查询所有职位
    List<TitleCategory> queryAllTitleCategories();
}
