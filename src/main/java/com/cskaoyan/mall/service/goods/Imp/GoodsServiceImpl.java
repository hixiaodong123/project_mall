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

import java.util.*;

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
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;

    @Override
    public Map<String, Object> selectGoodsByGoodSnAndName(int page, int limit, String name, String goodsSn, String sort, String order) {
        PageHelper.startPage(page,limit);
        String name1="%"+name+"%";
        List<Goods> goods = goodsMapper.selectGoodsByGoodSnAndName(name1,goodsSn,sort,order);
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
    public Map<String, Object> selectGoodsByGoodsName(int page, int limit, String goodsName, String sort, String order) {
        PageHelper.startPage(page,limit);
        String name1="%"+goodsName+"%";
        List<Goods> goods = goodsMapper.selectGoodsByGoodsName(name1,sort,order);
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
        List<GoodsAttribute> attributes = goodsAttributeMapper.selectGoodsAttributeByGoodsId(id);
        map1.put("attributes",attributes);
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        Integer categoryId = goods.getCategoryId();
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        Byte sortOrder = category.getSortOrder();
        Integer firstLevel = categoryMapper.selectFirstLevelBySortOrder(sortOrder);
        int[] arr={firstLevel,categoryId};
        map1.put("categoryIds",arr);
        map1.put("goods",goods);
        List<GoodsProduct> products = goodsProductMapper.selectGoodsProductByGoodsId(id);
        map1.put("products",products);
        List<GoodsSpecification> specifications = goodsSpecificationMapper.selectGoodsSpecificationByGoodsId(id);
        map1.put("specifications",specifications);
        return ReturnMapUntil.returnMap(map1,"成功",0);
    }

    @Override
    public Map<String, Object> updateGoods(UpdateGoods map) {
        Date date = new Date();
        GoodsAttribute[] attributes = map.getAttributes();
        Goods goods = map.getGoods();
        GoodsProduct[] products = map.getProducts();
        GoodsSpecification[] specifications = map.getSpecifications();
        for (GoodsAttribute attribute : attributes) {
            if(attribute.getAttribute()==null && attribute.getValue()==null){
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("errmsg","商品参数为空");
                map2.put("errno",401);
                return map2;
            }
            else if(attribute.getAttribute()==null||attribute.getValue()==null){
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("errmsg","参数不对");
                map2.put("errno",401);
                return map2;
            }else {
                String attribute1 = attribute.getAttribute();
                String value = attribute.getValue();
                int i = goodsAttributeMapper.selectGoodsAttributeByAttributeAndValue(attribute1,value);
                if(i==0){
                    Integer id = goods.getId();
                    attribute.setGoodsId(id);
                    attribute.setAddTime(date);
                    attribute.setUpdateTime(date);
                    attribute.setDeleted(false);
                    goodsAttributeMapper.insert(attribute);
                }else{
                    attribute.setUpdateTime(date);
                    goodsAttributeMapper.updateByPrimaryKeySelective(attribute);
                }
            }
        }
        goodsMapper.updateByPrimaryKeySelective(goods);
        for (GoodsProduct product : products) {
            if(product.getId()!=0){
                GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(product.getId());
                if(!goodsProduct.equals(product)){
                    product.setUpdateTime(date);
                    goodsProductMapper.updateByPrimaryKeySelective(product);
                }
            }else {
                product.setAddTime(date);
                product.setUpdateTime(date);
                product.setDeleted(false);
                product.setGoodsId(goods.getId());
                goodsProductMapper.insert(product);
            }
        }
        for (GoodsSpecification specification : specifications) {
            if(specification.getId()!=null){
                GoodsSpecification specification1 = goodsSpecificationMapper.selectByPrimaryKey(specification.getId());
                if(!specification.equals(specification1)){
                    specification.setUpdateTime(date);
                    goodsSpecificationMapper.updateByPrimaryKeySelective(specification);
                }
            }else{
                specification.setAddTime(date);
                specification.setUpdateTime(date);
                specification.setDeleted(false);
                specification.setGoodsId(goods.getId());
                goodsSpecificationMapper.insert(specification);
            }
        }
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("errmsg","成功");
        map1.put("errno",0);
        return map1;
    }

    @Override
    public Map<String, Object> createGoods(UpdateGoods map) {
        Date date = new Date();
        GoodsAttribute[] attributes = map.getAttributes();
        Goods goods = map.getGoods();
        GoodsProduct[] products = map.getProducts();
        GoodsSpecification[] specifications = map.getSpecifications();
        goods.setAddTime(date);
        goods.setUpdateTime(date);
        goods.setDeleted(false);
        Integer goodsId = Integer.valueOf(goods.getGoodsSn());
        goods.setId(goodsId);
        goodsMapper.insertSelective(goods);
        for (GoodsAttribute attribute : attributes) {
            if(attribute.getAttribute()==null && attribute.getValue()==null){
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("errmsg","商品参数为空");
                map2.put("errno",401);
                return map2;
            }
            else if(attribute.getAttribute()==null||attribute.getValue()==null){
                HashMap<String, Object> map2 = new HashMap<>();
                map2.put("errmsg","参数不对");
                map2.put("errno",401);
                return map2;
            }else {
                    attribute.setGoodsId(goodsId);
                    attribute.setAddTime(date);
                    attribute.setUpdateTime(date);
                    attribute.setDeleted(false);
                    goodsAttributeMapper.insert(attribute);
            }
        }
        goodsMapper.updateByPrimaryKeySelective(goods);
        for (GoodsProduct product : products) {
            if(product.getId()!=0){
                GoodsProduct goodsProduct = goodsProductMapper.selectByPrimaryKey(product.getId());
                if(!goodsProduct.equals(product)){
                    product.setUpdateTime(date);
                    goodsProductMapper.updateByPrimaryKeySelective(product);
                }
            }else {
                product.setAddTime(date);
                product.setUpdateTime(date);
                product.setDeleted(false);
                product.setGoodsId(goodsId);
                goodsProductMapper.insert(product);
            }
        }
        for (GoodsSpecification specification : specifications) {
            if(specification.getId()!=null){
                GoodsSpecification specification1 = goodsSpecificationMapper.selectByPrimaryKey(specification.getId());
                if(!specification.equals(specification1)){
                    specification.setUpdateTime(date);
                    goodsSpecificationMapper.updateByPrimaryKeySelective(specification);
                }
            }else{
                specification.setAddTime(date);
                specification.setUpdateTime(date);
                specification.setDeleted(false);
                specification.setGoodsId(goodsId);
                goodsSpecificationMapper.insert(specification);
            }
        }
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("errmsg","成功");
        map1.put("errno",0);
        return map1;
    }

}
