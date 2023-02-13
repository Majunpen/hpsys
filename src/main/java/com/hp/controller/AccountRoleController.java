package com.hp.controller;


import com.hp.req.AccountRoleQuery;
import com.hp.resp.RespBean;
import com.hp.service.IAccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mjp
 * @since 2023-01-13
 */
@Controller
@RequestMapping("/accountRole")
public class AccountRoleController {
    @Autowired
    private IAccountRoleService accountRoleService;

    /**
     * 展示已经分配角色的用户列表
     * @param accountoleQuey
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> accountRoleList(AccountRoleQuery accountoleQuey){
        return accountRoleService.accountRoleList(accountoleQuey);
    }


}
