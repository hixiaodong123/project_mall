package com.cskaoyan.mall.controller.popularize;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.AdExample;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.popularize.AdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/ad")
public class AdController {

    @Autowired
    AdService adService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public BaseResponseModel<Map<String,Object>> list(int page, int limit, String sort, String order) {
        PageHelper.startPage(page, limit);
        List<Ad> ads = adService.selectByExample(new AdExample());
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
        System.out.println(ad);
        return null;
    }
}
