package com.cskaoyan.mall.controller.wx.person.collect;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.goods.GoodsService;
import com.cskaoyan.mall.service.person.collect.WxCollectService;
import com.cskaoyan.mall.utils.wx.UserTokenManager;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @description: 我的收藏模块
 * @author: Lime
 * @create: 2019-08-22 15:55
 **/

@RestController
@RequestMapping("/wx/collect")
public class WxCollectController
{

    private final WxCollectService wxCollectService;
    private final GoodsService goodsService;


    @Autowired
    public WxCollectController(WxCollectService wxCollectService, GoodsService goodsService)
    {
        this.wxCollectService = wxCollectService;
        this.goodsService = goodsService;
    }


    @GetMapping("/list")
    public BaseResponseModel list(HttpServletRequest request, Byte type)
    {

        //获取userId
        String token = request.getHeader("X-Litemall-Token");
        Integer userId = null;
        if (token != null && !"".equals(token))
        {
            userId = UserTokenManager.getUserId(token);
        }

        //初始化返回值,并且返回值的data域是一个map集合
        BaseResponseModel<Map<String, Object>> baseResponseModel = new BaseResponseModel<>();
        //初始化data域
        Map<String, Object> data = new HashMap<>();
        List<Collect> list = wxCollectService.findByType(type, userId);
        List<Goods> list1 = new ArrayList<>();
        for (Collect collect : list)
        {
            list1.add(goodsService.selectByPrimaryKey(collect.getValueId()));
        }
        data.put("collectList", list1);
        data.put("totalPage", 2);

        baseResponseModel.setData(data);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);


        return baseResponseModel;

    }

    @RequestMapping("/addordelete")
    public BaseResponseModel addordelete(@RequestBody Collect collect, HttpServletRequest request)
    {
        BaseResponseModel<Map<String, Object>> resp = new BaseResponseModel<>();
        try
        {
            Date date = new Date();
            collect.setAddTime(date);
            collect.setUpdateTime(date);
            collect.setDeleted(false);

            //获取userId
            String token = request.getHeader("X-Litemall-Token");
            Integer userId = null;
            if (token != null && !"".equals(token))
            {
                userId = UserTokenManager.getUserId(token);
            }
            collect.setUserId(userId);
            Collect flag = wxCollectService.getCollect(collect.getUserId(), collect.getType(), collect.getValueId());
            if (flag != null)
            {
                wxCollectService.delete(collect);
                Map<String, Object> map = new HashMap<>();
                map.put("type", "delete");
                resp.setData(map);
            }
            else
            {
                wxCollectService.addCollect(collect);
                Map<String, Object> map = new HashMap<>();
                map.put("type", "add");
                resp.setData(map);
            }
            resp.setErrno(0);
            resp.setErrmsg("成功");
        }
        catch (Exception e)
        {
            resp.setErrno(1);
            resp.setErrmsg("失败");
        }
        return resp;
    }


}
