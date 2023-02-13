package com.hp.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hp.dto.AccountRoleDto;
import com.hp.pojo.AccountRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hp.req.AccountRoleQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author mjp
 * @since 2023-01-13
 */
public interface AccountRoleMapper extends BaseMapper<AccountRole> {

    IPage<AccountRoleDto> accountRoleList(IPage<AccountRoleDto> page,@Param("accountRoleQuey") AccountRoleQuery accountRoleQuey);
}
