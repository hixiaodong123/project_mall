package com.cskaoyan.mall.service.wx_service.cart;

import org.apache.ibatis.annotations.Param;

public interface GoodsProductService {
    int queryGoodsProductNumByGoodsId(@Param("goodsId") Integer goodsId);
}
