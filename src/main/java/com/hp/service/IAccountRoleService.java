package com.hp.service;

import com.hp.pojo.AccountRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hp.req.AccountRoleQuery;
import com.hp.resp.RespBean;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mjp
 * @since 2023-01-13
 */
public interface IAccountRoleService extends IService<AccountRole> {

    RespBean saveRoleToUser(Integer roleId, Integer eId);

    Map<String, Object> accountRoleList(AccountRoleQuery accountoleQuey);
}
