package com.wyl.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author zyh
 * @Date 2022/12/13 17:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "login")
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private String phone;
    private String nickname;
    private Boolean sex;
    private String email;
    private String address;
    private String avatar;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)// 反序列化
    @JsonSerialize(using = LocalDateTimeSerializer.class)// 序列化
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime createTime;
    private Boolean permission;
    private Long functionId;
    @TableField(exist = false)
    private Function function;
}
