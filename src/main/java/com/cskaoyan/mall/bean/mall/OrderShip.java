package com.cskaoyan.mall.bean.mall;

public class OrderShip {
    private int orderId;

    private String shipChannel;

    private String shipSn;

    private int refundMoney;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getShipChannel() {
        return shipChannel;
    }

    public void setShipChannel(String shipChannel) {
        this.shipChannel = shipChannel;
    }

    public String getShipSn() {
        return shipSn;
    }

    public void setShipSn(String shipSn) {
        this.shipSn = shipSn;
    }

    public int getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(int refundMoney) {
        this.refundMoney = refundMoney;
    }
}
