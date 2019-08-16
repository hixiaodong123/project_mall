package com.cskaoyan.mall.bean.base;

/**
 * @description: 返回的json格式类
 * @author: Lime
 * @create: 2019-08-16 12:33
 **/

public class BaseResponseModel<T>
{
    T data;
    String errmsg;
    int errno;

    public BaseResponseModel()
    {
    }

    public BaseResponseModel(T data, String errmsg, int errno)
    {
        this.data = data;
        this.errmsg = errmsg;
        this.errno = errno;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public String getErrmsg()
    {
        return errmsg;
    }

    public void setErrmsg(String errmsg)
    {
        this.errmsg = errmsg;
    }

    public int getErrno()
    {
        return errno;
    }

    public void setErrno(int errno)
    {
        this.errno = errno;
    }
}
