package com.wyl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zyh
 * @Date 2022/12/13 18:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "goods")
public class Goods {
    private Long id;
    private String goodsName;
    private String goodsPrice;
    private String goodsUrl;
    private Integer goodsAmount;
    private String goodsInformation;
}
