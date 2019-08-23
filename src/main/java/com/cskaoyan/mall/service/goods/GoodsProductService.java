package com.cskaoyan.mall.service.goods;

import com.cskaoyan.mall.bean.GoodsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsProductService {
    List<GoodsProduct> selectGoodsProductsByGoodsId(int goodsId);

    int queryGoodsProductNumByGoodsId(@Param("goodsId") Integer goodsId);
}
