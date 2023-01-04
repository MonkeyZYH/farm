package com.wyl.controller;

import com.wyl.constants.HttpCode;
import com.wyl.controller.request.LoginRequest;
import com.wyl.entity.Login;
import com.wyl.entity.Result;
import com.wyl.service.Impl.SessionService;
import com.wyl.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zyh
 * @Date 2022/12/14 7:23
 */
@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private SessionService sessionService;

    @GetMapping("/findLoginFunction")
    public Result findLoginFunction(@RequestParam Long id){
        try {
           return Result.success(loginService.findLoginFunction(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(HttpCode.BAD_REQUEST,"查询失败!");
        }
    }
    @PostMapping("/updateLogin")
    public Result updateLogin(@RequestBody Login login){
        try {
            return Result.success(loginService.updateLogin(login));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(HttpCode.BAD_REQUEST, "更新失败!");
        }
    }

    @PostMapping("/addLogin")
    public Result addLogin(@RequestBody Login login){
        return Result.success(loginService.addLogin(login));
    }

    @PostMapping("/loginUser")
    public Result loginUser(@RequestBody LoginRequest loginRequest){
        return Result.success(sessionService.login(loginRequest));
    }

    @PostMapping("/loginsms")
    public Result loginsms(@RequestBody LoginRequest request){
        return Result.success(sessionService.loginWithSms(request));
    }

    /**
     * 获取手机验证码
     * @param account
     * @return
     */
    @GetMapping("/sms")
    public Result login(@RequestParam String account){
        sessionService.sendSms(account);
        return Result.success();
    }
}
