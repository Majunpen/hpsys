package com.hp.service;

import com.hp.pojo.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mjp
 * @since 2023-01-13
 */
public interface IPermissionService extends IService<Permission> {

    List<Integer> queryRoleHasAllMenuIdsByRoleId(Integer roleId);

    List<String> findAuthorityByUserName(String username);
}
