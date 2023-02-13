package com.hp.mapper;

import com.hp.pojo.TitleCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mjp
 * @since 2023-01-10
 */
public interface TitleCategoryMapper extends BaseMapper<TitleCategory> {
    /**
     * 查询所有的职位分类
     * @return
     */
    List<TitleCategory> selectAllTitleCategory();
}
