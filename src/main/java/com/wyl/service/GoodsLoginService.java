package com.wyl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wyl.entity.GoodsLogin;
import com.wyl.entity.Result;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zyh
 * @Date 2022/12/18 16:53
 */
@Repository
public interface GoodsLoginService extends IService<GoodsLogin> {
    /**
     * 根据用户id去查询购物车信息(goods_login表)
     * @param id
     * @return
     */
    List<GoodsLogin> findLoginCart(Long id);

}
