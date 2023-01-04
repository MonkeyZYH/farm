package com.wyl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyl.entity.Comment;
import com.wyl.entity.Login;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author zyh
 * @Date 2022/12/13 17:58
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
