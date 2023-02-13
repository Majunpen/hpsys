package com.hp.service;

import com.hp.dto.DeptDto;
import com.hp.pojo.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mjp
 * @since 2023-01-05
 */
public interface IDeptService extends IService<Dept> {
    /**
     * 查询所有的部门
     * @return
     */
    Map<String,Object> deptList();

    /**
     * 添加部门
     * @param dept
     */
    void saveDept(Dept dept);
    //修改部门
    void updateDept(Dept dept);

    void deleteDept(Integer id);

    //查询所有的部门
    List<DeptDto> findAllDept();
}
