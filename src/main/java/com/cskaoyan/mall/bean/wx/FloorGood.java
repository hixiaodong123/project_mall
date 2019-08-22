package com.cskaoyan.mall.bean.wx;

import com.cskaoyan.mall.bean.Goods;

import java.util.List;

public class FloorGood {
    private List<Goods> goodsList;

    private Integer Id;

    private String name;

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
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
