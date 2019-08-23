package com.cskaoyan.mall.service.person.foot;

import com.cskaoyan.mall.bean.Footprint;

import java.util.List;

/**
 * @description: 足迹的业务处理
 * @author: Lime
 * @create: 2019-08-23 12:10
 **/

public interface UserFootPrintService
{
    List<Footprint> queryFootPrint(int page, int size, Integer userId, Integer goodsId);

    int countFootprintById(Integer userId);
}
