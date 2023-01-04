package com.wyl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author zyh
 * @Date 2023/1/3 10:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "Carousel_figure")
public class CarouselFigure {
    private Long id;
    private String picUrl;
}
