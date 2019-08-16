package com.cskaoyan.mall.service.system;

import java.util.Map;

/**
 * @description: 配置管理业务层接口
 * @author: Lime
 * @create: 2019-08-16 18:07
 **/

public interface SystemService
{


    /**
     * 通过mall这个词查询出所需要的数据,并封装成map
     */
    Map<String, Object> findByMallWord();

    /**
     * 更新mall记录,传入的是map
     */
    void updateMall(Map<String, Object> map);


    /**
     * 通过express这个关键词查询出所需要的数据,并封装成map
     */
    Map<String, Object> findByExpressWord();

    /**
     * 更新express记录,传入的是map
     */
    void updateExpress(Map<String, Object> map);

    /**
     * 通过order这个关键词查询出所需要的数据,并封装成map
     */
    Map<String, Object> findByOrderWord();

    /**
     * 更新order记录,传入的是map
     */
    void updateOrder(Map<String, Object> map);

    /**
     * 通过wx这个关键词查询出所需要的数据,并封装成map
     */
    Map<String, Object> findByWxWord();

    /**
     * 更新wx记录,传入的是map
     */
    void updateWx(Map<String, Object> map);
}
