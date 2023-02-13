package com.hp.service.impl;

import com.hp.pojo.Permission;
import com.hp.mapper.PermissionMapper;
import com.hp.service.IPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mjp
 * @since 2023-01-13
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<Integer> queryRoleHasAllMenuIdsByRoleId(Integer roleId) {
        return this.baseMapper.queryRoleHasAllMenuIdsByRoleId(roleId);
    }

    @Override
    public List<String> findAuthorityByUserName(String username) {
        return this.baseMapper.findAuthorityByUserName(username);
    }
}
