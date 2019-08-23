package com.cskaoyan.mall.service.goods;

import com.cskaoyan.mall.bean.GoodsSpecification;

import java.util.List;
import java.util.Map;

public interface GoodsSpecificationService {
    List<Map>selectGoodsSpecificationByGoodsId(int goodsId);
}
