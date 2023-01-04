package com.wyl.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyl.entity.Comment;
import com.wyl.mapper.CommentMapper;
import com.wyl.service.CommentService;
import org.springframework.stereotype.Service;

/**
 * @Author zyh
 * @Date 2022/12/13 17:59
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    
}
