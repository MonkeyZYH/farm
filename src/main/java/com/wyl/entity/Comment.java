package com.wyl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author zyh
 * @Date 2022/12/13 18:33
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "comment")
public class Comment {
    private Long id;
    private String content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;
    private Long goodsId;
    private Long loginId;
    @TableField(exist = false)
    private Goods goods;
    @TableField(exist = false)
    private Login login;
}
