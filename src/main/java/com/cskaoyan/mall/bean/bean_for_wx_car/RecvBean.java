package com.cskaoyan.mall.bean.bean_for_wx_car;

public class RecvBean {
    int[] ProductIds;
    Integer isChecked;

    public int[] getProductIds() {
        return ProductIds;
    }

    public void setProductIds(int[] productIds) {
        ProductIds = productIds;
    }

    public Integer getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(Integer isChecked) {
        this.isChecked = isChecked;
    }
}
