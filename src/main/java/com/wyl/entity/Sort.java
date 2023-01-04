package com.wyl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zyh
 * @Date 2022/12/13 18:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sort")
public class Sort {
    private Long id;
    private String sortName;
}
