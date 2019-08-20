package com.cskaoyan.mall.controller.popularize;

import com.cskaoyan.mall.bean.Coupon;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.popularize.GoodsService;
import com.cskaoyan.mall.service.popularize.GrouponRulesService;
import com.cskaoyan.mall.service.popularize.GrouponService;
import com.cskaoyan.mall.utils.popularize.PopularizeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("admin/groupon")
public class GrouponController {

    @Autowired
    GoodsService goodsService;

    @Autowired
    GrouponService grouponService;

    @Autowired
    GrouponRulesService grouponRulesService;

        @RequestMapping(value = "list", method = RequestMethod.GET)
        public BaseResponseModel<Map<String,Object>> list(int page, int limit,Integer goodsId,String sort, String order) {
            PageHelper.startPage(page, limit);
            List<GrouponRules> grouponRules = grouponRulesService.selectByConditions(goodsId, sort, order);
            PageInfo<GrouponRules> grouponRulesPageInfo = new PageInfo<>(grouponRules);
            long total = grouponRulesPageInfo.getTotal();
            Map<String, Object> map = new HashMap<>();
            map.put("items", grouponRules);
            map.put("total", total);
            BaseResponseModel<Map<String, Object>> baseResponseModel = new BaseResponseModel<>();
            baseResponseModel.setData(map);
            baseResponseModel.setErrmsg("成功");
            baseResponseModel.setErrno(0);
            return baseResponseModel;
        }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public BaseResponseModel<GrouponRules> creat(@RequestBody GrouponRules grouponRules) {
        Integer goodsId = grouponRules.getGoodsId();
        Goods goods = goodsService.selectByPrimaryKey(goodsId);
        grouponRules.setAddTime(new Date());
        grouponRules.setDeleted(false);
        grouponRules.setUpdateTime(new Date());
        grouponRules.setPicUrl(goods.getPicUrl());
        grouponRules.setGoodsName(goods.getName());
        int insert = grouponRulesService.insert(grouponRules);

        BaseResponseModel<GrouponRules> grouponRulesBaseResponseModel = PopularizeUtils.respValue(grouponRules, insert);
        return grouponRulesBaseResponseModel;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public BaseResponseModel<GrouponRules> update(@RequestBody GrouponRules grouponRules) {
        grouponRules.setUpdateTime(new Date());
        int update = grouponRulesService.updateByPrimaryKeySelective(grouponRules);
        BaseResponseModel<GrouponRules> grouponRulesBaseResponseModel = PopularizeUtils.respValue(grouponRules, update);
        return grouponRulesBaseResponseModel;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Map<String,Object> delete(@RequestBody GrouponRules grouponRules) {
        grouponRules.setDeleted(true);
        int update = grouponRulesService.updateByPrimaryKeySelective(grouponRules);
        BaseResponseModel<GrouponRules> grouponRulesBaseResponseModel = PopularizeUtils.respValue(grouponRules, update);
        HashMap<String, Object> map = new HashMap<>();
        map.put("errmsg", "成功");
        map.put("errno", 0);
        return map;
    }

    @RequestMapping(value = "listRecord", method = RequestMethod.GET)
    public BaseResponseModel<Map<String,Object>> listRecord(int page, int limit,Integer goodsId,String sort, String order) {
        PageHelper.startPage(page, limit);

        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();

        List<Groupon> groupons = grouponService.selectByConditions(goodsId, sort, order);
        for (Groupon groupon : groupons) {
            GrouponRules grouponRules = grouponRulesService.selectByPrimaryKey(groupon.getRulesId());
            Goods goods = goodsService.selectByPrimaryKey(grouponRules.getGoodsId());
            map.put("goods",goods);
            map.put("groupon", groupon);
            map.put("rules",grouponRules);
            map.put("subGroupon", new ArrayList<>());
            maps.add(map);
        }

        PageInfo<Groupon> grouponPageInfo = new PageInfo<>(groupons);
        long total = grouponPageInfo.getTotal();
        Map<String, Object> map01 = new HashMap<>();
        map01.put("items", maps);
        map01.put("total", total);
        BaseResponseModel<Map<String, Object>> baseResponseModel = new BaseResponseModel<>();
        baseResponseModel.setData(map01);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        return baseResponseModel;
    }
}
