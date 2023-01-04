package com.wyl.controller;

import com.wyl.entity.CarouselFigure;
import com.wyl.entity.Goods;
import com.wyl.entity.Result;
import com.wyl.handler.ServiceException;
import com.wyl.mapper.CarouselFigureMapper;
import com.wyl.mapper.GoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author zyh
 * @Date 2023/1/2 18:18
 */
@RestController
@RequestMapping("/image")
@Transactional
public class ImageUploadController {

    @Autowired
    private CarouselFigureMapper carouselFigureMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Value(value = "${upload.path}")
    private String uploadPath;

    /**
     * 上传图片到轮播图(Carousel_figure)表
     * @param multipartFile
     * @return
     */
    @PostMapping("/uploadFigure")
    public Result uploadFigure(@RequestParam(value = "file") MultipartFile multipartFile) {
        CarouselFigure figure = new CarouselFigure();
        figure.setPicUrl("localhost:8089/"+upload(multipartFile));
        carouselFigureMapper.insert(figure);
            return Result.success(figure);
    }

    /**
     * 根据id删除轮播图(Carousel_figure)表的数据
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        try{
            return Result.success(carouselFigureMapper.deleteById(id));
        }catch (Exception e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    /**
     * 查询轮播图(Carousel_figure)表所有数据
     * @return
     */
    @GetMapping("/select")
    public Result selectCarouselFigure(){
        return Result.success(carouselFigureMapper.selectList(null));
    }

    /**
     * 上传图片并预览
     */
    public String upload(MultipartFile multipartFile){
        try {
            if (multipartFile.isEmpty()) {
                return "文件为空,请重新选择!";
            }

            // 上传的图片全部保存在 "E://upload//avatar//" 目录下
            File file = new File(uploadPath);
            if (!file.exists()) {
                // 创建完整的目录
                file.mkdirs();
            }

            // 获取文件原始名(包含后缀名)
            String orgName = multipartFile.getOriginalFilename();
            // 获取文件名（不包括后缀）
            String prefixName = orgName.substring(0, orgName.lastIndexOf("."));
            // 获取文件后缀名
            String suffixName = orgName.substring(orgName.lastIndexOf("."));
            // 这是处理后的新文件名
            String fileName;

            if(orgName.contains(".")) {
                // 示例：avatar.123.png，经过处理后得到：avatar.123_1661136943533.png
                fileName = prefixName + "_" + System.currentTimeMillis() + suffixName;
            } else {
                // 上传的图片没有后缀（这压根就不算是一个正常的图片吧？）
                return "上传图片格式错误,请重新选择！";
            }

            String savePath = file.getPath() + File.separator + fileName;
            File saveFile = new File(savePath);
            // 将上传的文件复制到指定目录
            FileCopyUtils.copy(multipartFile.getBytes(), saveFile);

            // 返回给前端的图片保存路径；前台可以根据返回的路径拼接完整地址，即可在浏览器上预览该图片
            String path = "upload/avatar" + File.separator + fileName;
//            CarouselFigure figure = new CarouselFigure();
            if (path.contains("\\")) {
                path = path.replace("\\", "/");
            }
//            figure.setPicUrl(path);
//            carouselFigureMapper.insert(figure);
            return path;
        } catch (IOException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

}
