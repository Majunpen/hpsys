package com.hp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.hp.pojo.Account;
import com.hp.mapper.AccountMapper;
import com.hp.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.xml.internal.ws.policy.AssertionSet;
import com.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.SoftAssertionsRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mjp
 * @since 2023-01-05
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Override
    public Account findAccountByUserName(String userName) {
        return this.baseMapper.selectOne(new QueryWrapper<Account>().eq("user_name",userName));
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void updatePassword(String oldPassword, String newPassword, String repeatPassword) {
        //判断传入的密码是否为空
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"原始密码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(repeatPassword),"重复密码不能为空");

        //判断两次输入的新密码是否一致
        AssertUtil.isTrue(!(newPassword.equals(repeatPassword)),"两次新密码不一致");
        //判断原始密码是否正确
        //获取到当前登录的用户名
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        //通过当前登录用户名获取登录对象
        Account accountByUserName = this.findAccountByUserName(name);
        //判断原始密码是否正确
        AssertUtil.isTrue(!(passwordEncoder.matches(oldPassword,accountByUserName.getPassword())),"原始密码不正确");
        //判断确认密码是否与新密码一致
        AssertUtil.isTrue(passwordEncoder.matches(newPassword,accountByUserName.getPassword()),"原始密码与新密码不同");

        //更新密码
        accountByUserName.setPassword(passwordEncoder.encode(newPassword));//密码加密
        AssertUtil.isTrue(this.baseMapper.updateById(accountByUserName)==0,"修改密码失败");
    }
}
