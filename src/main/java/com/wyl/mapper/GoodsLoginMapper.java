package com.wyl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyl.entity.GoodsLogin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author zyh
 * @Date 2022/12/18 16:28
 */
@Mapper
public interface GoodsLoginMapper extends BaseMapper<GoodsLogin> {

    /**
     * 根据用户id去查询购物车信息(goods_login表)
     * @param id
     * @return
     */
    List<GoodsLogin> findLoginCart(Long id);
}
