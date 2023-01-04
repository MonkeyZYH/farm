package com.wyl.controller;

import com.wyl.entity.Result;
import com.wyl.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author zyh
 * @Date 2022/12/17 23:59
 */
@RestController
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping
    public Result selectAllGoods(@RequestParam Integer pageNum, Integer pageSize){
        return goodsService.selectAllGoods(pageNum,pageSize);
    }
}
