package com.wyl.controller.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = "用户名不能为空！")
    private String username;
    private String password;
    private String smsCode;
}
