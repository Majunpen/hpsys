package com.hp.mapper;

import com.hp.dto.DeptDto;
import com.hp.pojo.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mjp
 * @since 2023-01-05
 */
public interface DeptMapper extends BaseMapper<Dept> {

    List<Dept> selectAllDepts();

    /**
     * 根据上级节点的id，查询下级列表
     * @param parentId
     * @return
     */
    List<DeptDto> findDeptDtoByParentDeptId(@Param("parentId") Integer parentId);
}
