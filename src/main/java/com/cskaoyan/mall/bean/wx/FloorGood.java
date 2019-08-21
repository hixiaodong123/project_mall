package com.cskaoyan.mall.bean.wx;

import com.cskaoyan.mall.bean.Goods;

import java.util.List;

public class FloorGood {
    private List<Goods> goods;

    private Integer Id;

    private String name;

    public List<Goods> getGoods() {
        return goods;
    }

    public void setGoods(List<Goods> goods) {
        this.goods = goods;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
