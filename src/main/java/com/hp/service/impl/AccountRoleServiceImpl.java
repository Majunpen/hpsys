package com.hp.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hp.dto.AccountRoleDto;
import com.hp.pojo.AccountRole;
import com.hp.mapper.AccountRoleMapper;
import com.hp.req.AccountRoleQuery;
import com.hp.resp.RespBean;
import com.hp.service.IAccountRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.utils.PageResultUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mjp
 * @since 2023-01-13
 */
@Service
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleMapper, AccountRole> implements IAccountRoleService {

    @Override //添加
    public RespBean saveRoleToUser(Integer roleId, Integer eId) {
        AccountRole accountRole = new AccountRole();
        accountRole.setAccountId(eId);
        accountRole.setRoleId(roleId);
        this.baseMapper.insert(accountRole);
        return RespBean.success("记录添加成功");

    }

    @Override
    public Map<String, Object> accountRoleList(AccountRoleQuery accountRoleQuey) {
        IPage<AccountRoleDto> page = new Page<>(accountRoleQuey.getPage(),accountRoleQuey.getLimit());
        page = this.baseMapper.accountRoleList(page,accountRoleQuey);
        return PageResultUtils.getResult(page.getTotal(),page.getRecords());
    }

}
