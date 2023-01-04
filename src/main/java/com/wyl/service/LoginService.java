package com.wyl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyl.entity.Login;
import org.springframework.stereotype.Repository;

/**
 * @Author zyh
 * @Date 2022/12/13 17:59
 */
@Repository
public interface LoginService extends IService<Login> {
    /**
     * 根据permission判断客户还是商家进行功能区分(关联function表)
     * @param id
     * @return
     */
    Login findLoginFunction(Long id);

    /**
     * 修改用户的信息(login表)
     * @param login
     * @return
     */
    Boolean updateLogin(Login login);

    /**
     * 注册用户(login表)
     * @param login
     * @return
     */
    Boolean addLogin(Login login);

    /**
     * 获取用户名(login表)
     * @param username
     * @return
     */
    Login getByUsername(String username);

    /**
     * 获取手机号(login表)
     * @param usrername
     * @return
     */
    Login getByPhone(String usrername);
}
