package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVo;

import java.util.List;

public interface PromotionAdService {
    /*
     分页查询广告信息
    */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo);

    /*
    新建广告位信息
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /*
    修改课程信息
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /*
   回显广告位信息
    */
    PromotionAd findPromotionAdById(Integer id);

    /*
   广告动态上下线
    */
    public void updatePromotionAdStatus(int id,int status);
}
