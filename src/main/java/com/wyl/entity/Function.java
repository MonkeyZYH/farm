package com.wyl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zyh
 * @Date 2022/12/13 18:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "function")
public class Function {
    private Long id;
    private Boolean cart;
    private Boolean order;
    private Boolean collect;
    private Boolean publish;
    private Boolean revise;
}
