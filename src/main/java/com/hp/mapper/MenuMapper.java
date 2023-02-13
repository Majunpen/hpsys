package com.hp.mapper;

import com.hp.dto.TreeDto;
import com.hp.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mjp
 * @since 2023-01-13
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<TreeDto> queryAllMenus();

    List<Menu> selectAllMenus();
}
