package com.cskaoyan.mall.bean.wx;

import com.cskaoyan.mall.bean.Goods;

public class GrouponWX {
    //保存商品信息
    private Goods goods;
    //保存当前组团人数。根据groupon表格的creator_user_id统计
    private Integer groupon_num;
    //保存团购价格，根据goods表格的retail_price和groupon_rules表格的discount相减获得
    private Integer groupon_price;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Integer getGroupon_num() {
        return groupon_num;
    }

    public void setGroupon_num(Integer groupon_num) {
        this.groupon_num = groupon_num;
    }

    public Integer getGroupon_price() {
        return groupon_price;
    }

    public void setGroupon_price(Integer groupon_price) {
        this.groupon_price = groupon_price;
    }
}
