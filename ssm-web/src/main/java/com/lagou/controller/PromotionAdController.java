package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /*
    广告分页查询
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVo promotionAdVo){
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVo);

        return new ResponseResult(true,200,"广告分页查询成功",pageInfo);
    }

    /*
    图片上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file")MultipartFile file, HttpServletRequest request) throws IOException {
        //判断文件是否为空
        if(file.isEmpty()){
            throw new RuntimeException();
        }

        //获取项目部署路径
        String realPath = request.getServletContext().getRealPath("/");
        String webappPath = realPath.substring(0, realPath.indexOf("ssm-web"));

        //获取原文件名
        String originalFilename = file.getOriginalFilename();

        //生成新文件名
        String newFileName=System.currentTimeMillis()+originalFilename.substring(originalFilename.lastIndexOf("."));

        //文件上传
        String uploadPath = webappPath+"upload\\";
        File filePath = new File(uploadPath, newFileName);

        //如果目录不存在 创建目录
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录"+filePath);
        }

        file.transferTo(filePath);

        //将文件名和文件路径返回
        HashMap<String, String> map = new HashMap<>();
        map.put("fileName",newFileName);
        map.put("filePath","http://localhost:8080/upload/"+newFileName);
        return new ResponseResult(true,200,"上传成功",map);
    }

    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){
        if(promotionAd.getId()!=null){
            promotionAdService.updatePromotionAd(promotionAd);
            return new ResponseResult(true, 200, "更新广告成功", null);
        }else {
            promotionAdService.savePromotionAd(promotionAd);
            return new ResponseResult(true, 200, "新建广告成功", null);
        }
    }

    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionAdById(Integer id){
        PromotionAd promotionAdById = promotionAdService.findPromotionAdById(id);
        return new ResponseResult(true,200,"响应成功",promotionAdById);
    }

    @RequestMapping("updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id,Integer status){
        promotionAdService.updatePromotionAdStatus(id,status);
        Map<String,Integer> map=new HashMap<>();
        map.put("status",status);
        return new ResponseResult(true,200,"相应成功",map);
    }
}
