package com.cskaoyan.mall.controller.webController.popularize;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.popularize.AdService;
import com.cskaoyan.mall.utils.popularize.PopularizeUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/ad")
public class AdController {

    @Autowired
    AdService adService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public BaseResponseModel<Map<String,Object>> list(int page, int limit,String name,String content,String sort, String order) {
        PageHelper.startPage(page, limit);
        List<Ad> ads = adService.selectAll(name,content,sort,order);
        PageInfo<Ad> adPageInfo = new PageInfo<>(ads);
        long total = adPageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("items", ads);
        map.put("total", total);
        BaseResponseModel<Map<String, Object>> baseResponseModel = new BaseResponseModel<>();
        baseResponseModel.setData(map);
        baseResponseModel.setErrmsg("成功");
        baseResponseModel.setErrno(0);
        return baseResponseModel;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public BaseResponseModel<Ad> creat(@RequestBody Ad ad) {
        ad.setAddTime(new Date());
        ad.setUpdateTime(new Date());
        ad.setDeleted(false);
        int insert = adService.insert(ad);
        BaseResponseModel<Ad> adBaseResponseModel = PopularizeUtils.respValue(ad, insert);
        return adBaseResponseModel;
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public BaseResponseModel<Ad> update(@RequestBody Ad ad) {
        ad.setUpdateTime(new Date());
        int update = adService.updateByPrimaryKeySelective(ad);
        BaseResponseModel<Ad> adBaseResponseModel = PopularizeUtils.respValue(ad, update);
        return adBaseResponseModel;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Map<String,Object> delete(@RequestBody Ad ad) {
        ad.setDeleted(true);
        int update = adService.updateByPrimaryKeySelective(ad);
        BaseResponseModel<Ad> adBaseResponseModel = PopularizeUtils.respValue(ad, update);
        HashMap<String, Object> map = new HashMap<>();
        map.put("errmsg", "成功");
        map.put("errno", 0);
        return map;
    }
}
