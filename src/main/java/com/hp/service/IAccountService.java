package com.hp.service;

import com.hp.pojo.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mjp
 * @since 2023-01-05
 */
public interface IAccountService extends IService<Account> {
    //根据用户名查询用户对象
    Account findAccountByUserName(String userName);

    void updatePassword(String oldPassword, String newPassword, String repeatPassword);
}
