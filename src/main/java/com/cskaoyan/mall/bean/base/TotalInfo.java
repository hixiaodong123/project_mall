package com.cskaoyan.mall.bean.base;

/**
 * @description: 首页总条目信息
 * @author: Lime
 * @create: 2019-08-16 13:57
 **/

public class TotalInfo
{
    private Long goodsTotal;
    private Long userTotal;
    private Long productTotal;
    private Long orderTotal;

    public TotalInfo()
    {
    }

    public TotalInfo(Long goodsTotal, Long userTotal, Long productTotal, Long orderTotal)
    {
        this.goodsTotal = goodsTotal;
        this.userTotal = userTotal;
        this.productTotal = productTotal;
        this.orderTotal = orderTotal;
    }

    public Long getGoodsTotal()
    {
        return goodsTotal;
    }

    public void setGoodsTotal(Long goodsTotal)
    {
        this.goodsTotal = goodsTotal;
    }

    public Long getUserTotal()
    {
        return userTotal;
    }

    public void setUserTotal(Long userTotal)
    {
        this.userTotal = userTotal;
    }

    public Long getProductTotal()
    {
        return productTotal;
    }

    public void setProductTotal(Long productTotal)
    {
        this.productTotal = productTotal;
    }

    public Long getOrderTotal()
    {
        return orderTotal;
    }

    public void setOrderTotal(Long orderTotal)
    {
        this.orderTotal = orderTotal;
    }
}
