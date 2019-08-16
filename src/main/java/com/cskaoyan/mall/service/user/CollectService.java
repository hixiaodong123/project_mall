package com.cskaoyan.mall.service.user;

import com.cskaoyan.mall.bean.Collect;

import java.util.List;

public interface CollectService {
    List<Collect> listColletByCondition(String userId, String valueId);
}
