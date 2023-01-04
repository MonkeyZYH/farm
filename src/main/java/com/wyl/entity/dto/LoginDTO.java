package com.wyl.entity.dto;

import com.wyl.entity.Login;
import com.wyl.entity.LoginUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO implements Serializable {
    private static final long serialVersionUID = 3181923417136796145L;

    private String token;
    private Login login;

}
