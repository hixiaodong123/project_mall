package com.cskaoyan.mall.service.goods;

import com.cskaoyan.mall.bean.GoodsAttribute;

import java.util.List;

public interface GoodsAttributeService {
    List<GoodsAttribute> selectAttributeByGoodsId(int goodsId);
}
