package com.cskaoyan.mall.controller.wx;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.bean.wx.FloorGood;
import com.cskaoyan.mall.bean.wx.GrouponWX;
import com.cskaoyan.mall.service.goods.GoodsService;
import com.cskaoyan.mall.service.mall.BrandService;
import com.cskaoyan.mall.service.mall.CategoryService;
import com.cskaoyan.mall.service.popularize.CouponService;
import com.cskaoyan.mall.service.popularize.GrouponRulesService;
import com.cskaoyan.mall.service.popularize.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/home")
public class WxHomeController {
    @Autowired
    BrandService brandService;//品牌制造商
    @Autowired
    CouponService couponService;//优惠券
    @Autowired
    GoodsService goodsService;//商品（hotGoods，newGoods）
    @Autowired
    TopicService topicService;//专题
    @Autowired
    GrouponRulesService grouponRulesService;//团购
    @Autowired
    CategoryService categoryService;//分类

    //设置显示数目，避免网页响应时间过长
    int brandListSize = 4;
    int topicListSize = 5;
    int categoryListSize = 3;
    int goodsListSize = 8;

    @RequestMapping("/index")
    public BaseResponseModel initHome(){
        //根据前台需要的json数据格式，从数据库查询数据
        //1.banner
        List<Map> banners = initBanner();
        //2.brandList数据
        List<Brand> brandList = brandService.queryBrandList(null, null, null, null);
        brandList = brandList.subList(0,brandListSize);
        //3.channel数据
        List<Category> channelList = categoryService.firstCategory();
        //4.couponList数据
        List<Coupon> couponList = couponService.selectAll(null,null,null,null,null);
        //5.floorGoodsList数据
        List<FloorGood> floorGoodsList = goodsService.selectFloorGoods(categoryListSize,goodsListSize);
        //6.grouponList数据
        List<GrouponWX> grouponList = grouponRulesService.selectGrouponWXList();
        //7.hotGoodsList数据
        List<Goods> hotGoodsList = goodsService.selectHotGoods();//查询isHot为true的商品
        //8.newGoodsList数据
        List<Goods> newGoodsList = goodsService.selectNewGoods();//查询isNew为true的商品
        //9.topicList数据
        List<Topic> topicList = topicService.selectByConditions(null, null, null, null);
        topicList = topicList.subList(0,topicListSize);

        //新建Map对象封装上述表格
        HashMap<String, Object> map = new HashMap<>();
        map.put("banner",banners);
        map.put("brandList",brandList);
        map.put("channel",channelList);
        map.put("couponList",couponList);
        map.put("floorGoodsList",floorGoodsList);
        map.put("grouponList",grouponList);
        map.put("hotGoodsList",hotGoodsList);
        map.put("newGoodsList",newGoodsList);
        map.put("topicList",topicList);
        //新建BaseResponseModel对象封装数据
        BaseResponseModel<Map<String, Object>> responseModel = new BaseResponseModel<>();
        responseModel.setData(map);
        responseModel.setErrmsg("成功");
        responseModel.setErrno(0);
        return responseModel;
    }


    //手动封装banner对象
    private List<Map> initBanner() {
        ArrayList<Map> list = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("addTime","2018-01-31 19:00:00");
        map.put("deleted",false);
        map.put("enabled",true);
        map.put("link","");
        map.put("position",1);
        map.put("updateTime","2018-01-31 19:00:00");
        map.put("content","合作 谁是你的菜");
        map.put("id",1);
        map.put("name","合作 谁是你的菜");
        map.put("url","http://yanxuan.nosdn.127.net/65091eebc48899298171c2eb6696fe27.jpg");
        list.add(map);
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("addTime","2018-01-31 19:00:00");
        map2.put("deleted",false);
        map2.put("enabled",true);
        map2.put("link","");
        map2.put("position",1);
        map2.put("updateTime","2018-01-31 19:00:00");
        map2.put("content","活动 美食节");
        map2.put("id",2);
        map2.put("name","活动 美食节");
        map2.put("url","http://yanxuan.nosdn.127.net/bff2e49136fcef1fd829f5036e07f116.jpg");
        list.add(map2);
        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("addTime","2018-01-31 19:00:00");
        map3.put("deleted",false);
        map3.put("enabled",true);
        map3.put("link","");
        map3.put("position",1);
        map3.put("updateTime","2018-01-31 19:00:00");
        map3.put("content","活动 母亲节");
        map3.put("url","http://yanxuan.nosdn.127.net/8e50c65fda145e6dd1bf4fb7ee0fcecc.jpg");
        list.add(map3);
        return list;
    }
}

