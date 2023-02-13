package com.hp.mapper;

import com.hp.pojo.Permission;
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
public interface PermissionMapper extends BaseMapper<Permission> {
    List<Integer> queryRoleHasAllMenuIdsByRoleId(Integer roleId);
    List<String> findAuthorityByUserName(String userName);


}
