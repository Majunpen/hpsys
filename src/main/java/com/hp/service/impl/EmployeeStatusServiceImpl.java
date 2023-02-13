package com.hp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hp.pojo.EmployeeStatus;
import com.hp.mapper.EmployeeStatusMapper;
import com.hp.service.IEmployeeStatusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mjp
 * @since 2023-01-10
 */
@Service
public class EmployeeStatusServiceImpl extends ServiceImpl<EmployeeStatusMapper, EmployeeStatus> implements IEmployeeStatusService {
@Autowired

    @Override
    public List<EmployeeStatus> queryAllEmployeeStatus() {

        return this.baseMapper.selectList(new QueryWrapper<EmployeeStatus>());
    }
}
