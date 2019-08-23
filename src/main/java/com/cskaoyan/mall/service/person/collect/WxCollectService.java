package com.cskaoyan.mall.service.person.collect;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.Goods;

import java.util.List;

/**
 * @description:
 * @author: Lime
 * @create: 2019-08-22 16:52
 **/

public interface WxCollectService
{

    List<Collect> findByType(Byte type, Integer userId);

    Collect getCollect(Integer userId, Byte type, Integer valueId);

    void delete(Collect collect);

    void addCollect(Collect collect);
}
