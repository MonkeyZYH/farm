package com.wyl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyl.entity.Goods;
import com.wyl.entity.Result;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zyh
 * @Date 2022/12/13 17:59
 */
@Repository
public interface GoodsService extends IService<Goods> {

    /**
     * mp自带分页查询总的商品(goods表)
     * @param pageNum
     * @param pageSize
     * @return
     */
   Result selectAllGoods(Integer pageNum, Integer pageSize);
}
