package com.cskaoyan.mall.service.goods;

import com.cskaoyan.mall.bean.GoodsProduct;

import java.util.List;

public interface GoodsProductService {
    List<GoodsProduct> selectGoodsProductsByGoodsId(int goodsId);
}
