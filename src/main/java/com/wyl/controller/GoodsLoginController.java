package com.wyl.controller;

import com.wyl.constants.HttpCode;
import com.wyl.entity.ErrorCode;
import com.wyl.entity.Result;
import com.wyl.service.GoodsLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zyh
 * @Date 2022/12/18 17:01
 */
@RestController
@RequestMapping("/cart")
public class GoodsLoginController {
    @Autowired
    private GoodsLoginService goodsLoginService;

    @GetMapping
    public Result findLoginCart(@RequestParam Long id){
        try {
            return Result.success(goodsLoginService.findLoginCart(id));
        }catch (Exception e){
            e.printStackTrace();
            return Result.error(HttpCode.BAD_REQUEST, "查询失败!");
        }

    }
}
