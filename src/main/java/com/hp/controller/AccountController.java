package com.hp.controller;


import com.hp.resp.RespBean;
import com.hp.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author mjp
 * @since 2023-01-05
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;
    /*进入到修改密码页面*/
    @GetMapping("/toPasswordPage")
    public String toPasswordPage(){
        return "account/password";
    }

    /*密码修改*/
    @PostMapping ("/updatePwd")
    @ResponseBody
    public RespBean updateUserPassword(String oldPassword,
                                       String newPassword,
                                       String repeatPassword){
        accountService.updatePassword(oldPassword,newPassword,repeatPassword);
        return RespBean.success("密码修改成功");
    }
}
