package com.cskaoyan.mall.bean;

public class Authorization {
    private int autid;

    private int pid;

    private String id;

    private String label;

    private Byte type;

    public int getAutid() {
        return autid;
    }

    public void setAutid(int autid) {
        this.autid = autid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}