package com.wyl.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyl.entity.Goods;
import com.wyl.entity.Result;
import com.wyl.mapper.GoodsMapper;
import com.wyl.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zyh
 * @Date 2022/12/13 17:59
 */
@Service
@Transactional(rollbackFor = Exception.class )
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Override
    public Result selectAllGoods(Integer pageNum, Integer pageSize) {
        Page<Goods> page = new Page<>(pageNum, pageSize);
        return Result.success(page(page,null));
    }
}
