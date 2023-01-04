package com.wyl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zyh
 * @Date 2022/12/18 16:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "goods_login")
public class GoodsLogin {
    private Long id;
    private Long goodsId;
    private Long loginId;
    private Integer cartAmount;
    @TableField(exist = false)
    private Goods goods;
}
