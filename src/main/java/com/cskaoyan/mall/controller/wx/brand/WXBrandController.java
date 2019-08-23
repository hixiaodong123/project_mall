package com.cskaoyan.mall.controller.wx.brand;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.mall.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wx/brand")
public class WXBrandController {
    @Autowired
    BrandService brandService;
    @RequestMapping("/detail")
    public BaseResponseModel detail(Integer id){
        Brand brand = brandService.selectByPrimaryKey(id);
        HashMap<String, Object> map = new HashMap<>();
        map.put("brand",brand);
        BaseResponseModel<Map> responseModel = new BaseResponseModel<>();
        responseModel.setErrmsg("成功");
        responseModel.setErrno(0);
        responseModel.setData(map);
        return responseModel;
    }
}
