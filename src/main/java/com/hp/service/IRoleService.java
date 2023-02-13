package com.hp.service;

import com.hp.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hp.resp.RespBean;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mjp
 * @since 2023-01-12
 */
public interface IRoleService extends IService<Role> {

    Map<String, Object> findList();

    RespBean saveRole(Role role);

    RespBean updateRole(Role role);

    void cancelRoleToUser(Integer roleId, Integer accountId);

    void addGrant(Integer[] mids, Integer roleId);
}


