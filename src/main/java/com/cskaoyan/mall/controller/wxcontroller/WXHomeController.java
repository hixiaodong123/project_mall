package com.cskaoyan.mall.controller.wxcontroller;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.bean.mall.L1Category;
import com.cskaoyan.mall.service.goods.GoodsService;
import com.cskaoyan.mall.service.mall.BrandService;
import com.cskaoyan.mall.service.mall.CategoryService;
import com.cskaoyan.mall.service.popularize.AdService;
import com.cskaoyan.mall.service.popularize.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wx")
public class WXHomeController {
    @Autowired
    AdService adService;

    @Autowired
    BrandService brandService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    CouponService couponService;

    @Autowired
    GoodsService goodsService;

    @RequestMapping("home/index")
    public BaseResponseModel homeIndex() {
        BaseResponseModel baseResponseModel = new BaseResponseModel();
        Map<String, Object> data = new HashMap<>();
        AdExample adExample = new AdExample();
        List<Ad> banner = adService.selectByExample(adExample);
        List<Brand> brandList = brandService.queryBrandListForWXHome();
        List<L1Category> channel = categoryService.queryL1Category();
        CouponExample couponExample = new CouponExample();
        List<Coupon> couponList = couponService.selectByExample(couponExample);
        Iterator<L1Category> iterator = channel.subList(0, 4).iterator();
        while (iterator.hasNext()) {
            // 根据level为L1的category的id查找所有level为L2的category
            List<Category> categories = categoryService.queryChildCategory(iterator.next().getId());
            // 根据level为L2的category的id去goods表中查找所有category_id与其相等的goods
            Iterator<Category> categoryIterator = categories.iterator();
            while (categoryIterator.hasNext()) {
                List<Goods> goodsList = goodsService.queryGoodsListByCategoryId(categoryIterator.next().getId());

            }

        }

    }
}
