package com.cskaoyan.mall.service.goods.Imp;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.otherbean.OtherBrand;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.service.goods.GoodsService;
import com.cskaoyan.mall.utils.ReturnMapUntil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public Map<String, Object> selectGoodsByGoodSnAndName(int page, int limit, String name, String goodsSn, String sort, String order) {
        PageHelper.startPage(page,limit);
        List<Goods> goods = goodsMapper.selectGoodsByGoodSnAndName(name,goodsSn,sort,order);
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        long total = pageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("items",goods);
        return map;
    }

    @Override
    public Map<String,Object> selectAllGoodsList(int page, int limit,String sort,String order) {
        PageHelper.startPage(page,limit);
        List<Goods> goods = goodsMapper.selectAllGoodsList(sort,order);
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        int total=goodsMapper.selectAllGoodsNum();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("items",goods);
        return map;
    }

    @Override
    public Map<String, Object> selectGoodsByGoodSn(int page, int limit, String goodsSn, String sort, String order) {
        PageHelper.startPage(page,limit);
        List<Goods> goods = goodsMapper.selectGoodsByGoodSn(goodsSn,sort,order);
        PageInfo<Goods> pageInfo = new PageInfo<>(goods);
        long total = pageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("items",goods);
        return map;
    }

    @Override
    public Map<String, Object> selectAllCatAndBrand() {
        List<Category> list3 = categoryMapper.selectAllCat("L1");
        List<OtherBrand> brand = brandMapper.selectAllBrand();
        ArrayList<Object> list = new ArrayList<>();
        for (Category category : list3) {
            Integer id = category.getId();
            List<Category> categories = categoryMapper.selectByPid(id);
            ArrayList<Object> list1 = new ArrayList<>();
            HashMap<String, Object> map2 = new HashMap<>();
            for (Category category1 : categories) {
                HashMap<String, Object> map1 = new HashMap<>();
                map1.put("label",category1.getName());
                map1.put("value",category1.getId());
                list1.add(map1);
            }
            map2.put("children",list1);
            map2.put("label",category.getName());
            map2.put("value",category.getId());
            list.add(map2);
        }
        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("brandList",brand);
        map3.put("categoryList",list);
        return ReturnMapUntil.returnMap(map3,"成功",0);
    }


    @Override
    public int deleteGoods(Goods goods) {
        return goodsMapper.deleteByPrimaryKey(goods.getId());
    }

    @Override
    public Map<String, Object> returnGoodsDetail(int id) {
        HashMap<String, Object> map1 = new HashMap<>();
        int[] arr1={1006002};
        int[] arr2 = {};
        map1.put("attributes",arr1);
        map1.put("categoryIds",arr2);
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        map1.put("goods",goods);
        List<GoodsProduct> products = goodsProductMapper.selectGoodsProductByGoodsId(id);
        map1.put("products",products);
        List<GoodsSpecification> specifications = goodsSpecificationMapper.selectGoodsSpecificationByGoodsId(id);
        map1.put("specifications",specifications);
        return ReturnMapUntil.returnMap(map1,"成功",0);
    }

}
