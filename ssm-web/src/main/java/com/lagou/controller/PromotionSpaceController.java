package com.lagou.controller;

import com.lagou.domain.PromotionSpace;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/PromotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){
        List<PromotionSpace> list = promotionSpaceService.findAllPromotionSpace();
        return new ResponseResult(true,200,"查询所有广告位成功",list);
    }

    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){
        if (promotionSpace.getId()!=null){
            promotionSpaceService.updatePromotionSpace(promotionSpace);
            return new ResponseResult(true,200,"修改广告位信息成功",null);
        }else {
            promotionSpaceService.savePromotionSpace(promotionSpace);
            return new ResponseResult(true,200,"保存广告位信息成功",null);
        }
    }

    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(int id){
        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);

        return new ResponseResult(true,200,"查询具体广告位成功",promotionSpace);
    }



}
