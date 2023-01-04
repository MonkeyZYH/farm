package com.wyl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wyl.entity.Login;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author zyh
 * @Date 2022/12/13 17:58
 */
@Mapper
public interface LoginMapper extends BaseMapper<Login> {
    /**
     * 根据permission判断客户还是商家进行功能区分(关联function表)
     * @param id
     * @return
     */
    Login findLoginFunction(Long id);

}
