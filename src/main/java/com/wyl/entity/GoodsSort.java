package com.wyl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zyh
 * @Date 2022/12/13 18:37
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "login")
public class GoodsSort {
    private Long id;
    private Long sortId;
    private Long goodsId;
    @TableField(exist = false)
    private Sort sort;
    @TableField(exist = false)
    private Goods goods;
}
