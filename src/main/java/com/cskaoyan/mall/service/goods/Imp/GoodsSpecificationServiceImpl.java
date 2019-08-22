package com.cskaoyan.mall.service.goods.Imp;

import com.cskaoyan.mall.bean.GoodsSpecification;
import com.cskaoyan.mall.mapper.GoodsSpecificationMapper;
import com.cskaoyan.mall.service.goods.GoodsSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsSpecificationServiceImpl implements GoodsSpecificationService {
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public List<Map> selectGoodsSpecificationByGoodsId(int goodsId) {
        List<Map> mapArrayList = new ArrayList<>();
        List<GoodsSpecification> goodsSpecificationList = goodsSpecificationMapper.selectGoodsSpecificationByGoodsId(goodsId);
        for (GoodsSpecification specification : goodsSpecificationList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("name",specification.getValue());
            map.put("valueList",new ArrayList<GoodsSpecification>().add(specification));
            mapArrayList.add(map);
        }
        return mapArrayList;
    }
}
