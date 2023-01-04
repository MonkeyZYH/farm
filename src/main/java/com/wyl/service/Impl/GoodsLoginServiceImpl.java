package com.wyl.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyl.entity.GoodsLogin;
import com.wyl.entity.Result;
import com.wyl.mapper.GoodsLoginMapper;
import com.wyl.service.GoodsLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zyh
 * @Date 2022/12/13 17:59
 */
@Service
public class GoodsLoginServiceImpl extends ServiceImpl<GoodsLoginMapper, GoodsLogin> implements GoodsLoginService {

    @Autowired
    private GoodsLoginMapper goodsLoginMapper;
    @Override
    public List<GoodsLogin> findLoginCart(Long id) {
        List<GoodsLogin> cart = goodsLoginMapper.findLoginCart(id);
        return cart;
    }
}
