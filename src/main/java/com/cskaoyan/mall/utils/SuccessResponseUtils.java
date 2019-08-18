package com.cskaoyan.mall.utils;

import com.cskaoyan.mall.bean.base.BaseResponseModel;

/**
 * @description: 返回成功的代码都一致，直接抽取出来封装成工具类
 * @author: Lime
 * @create: 2019-08-16 20:03
 **/

public class SuccessResponseUtils<T>
{
    public BaseResponseModel responseSuccess(T data)
    {
        BaseResponseModel<T> tBaseResponseModel = new BaseResponseModel<T>();
        tBaseResponseModel.setData(data);
        tBaseResponseModel.setErrmsg("成功");
        tBaseResponseModel.setErrno(0);
        return tBaseResponseModel;
    }
}
