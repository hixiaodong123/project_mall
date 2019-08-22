package com.cskaoyan.mall.controller.webController.mall;

import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.mall.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin")
public class RegionController {
    @Autowired
    RegionService regionService;

    @RequestMapping("region/list")
    public BaseResponseModel list() {
        List<Region> regions = regionService.queryRegionList();
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        baseResponseModel.setData(regions);
        baseResponseModel.setErrno(0);
        baseResponseModel.setErrmsg("成功");
        return baseResponseModel;
    }
}
