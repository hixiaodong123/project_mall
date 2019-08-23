package com.cskaoyan.mall.service.system.impl;

import com.cskaoyan.mall.mapper.SystemMapper;
import com.cskaoyan.mall.service.system.SystemService;
import com.cskaoyan.mall.utils.system.SystemResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @description: 配置管理业务实现
 * @author: Lime
 * @create: 2019-08-16 18:08
 **/

@Service
public class SystemServiceImpl implements SystemService
{
    private final SystemMapper systemMapper;

    @Autowired
    public SystemServiceImpl(SystemMapper systemMapper)
    {
        this.systemMapper = systemMapper;
    }

    @Override
    public Map<String, Object> findByMallWord()
    {
        //使用工具类，展示页面
        return SystemResponseUtils.view(systemMapper, "cskaoyan_mall_mall_%");
    }

    @Override
    public void updateMall(Map<String, Object> map)
    {
        //调用工具类的更新方法
        SystemResponseUtils.update(map, systemMapper);

    }

    @Override
    public Map<String, Object> findByExpressWord()
    {
        //使用工具类，展示页面
        return SystemResponseUtils.view(systemMapper, "%express%");
    }

    @Override
    public void updateExpress(Map<String, Object> map)
    {
        //调用工具类的更新方法
        SystemResponseUtils.update(map, systemMapper);
    }

    @Override
    public Map<String, Object> findByOrderWord()
    {
        //使用工具类，展示页面
        return SystemResponseUtils.view(systemMapper, "%order%");
    }

    @Override
    public void updateOrder(Map<String, Object> map)
    {
        //调用工具类的更新方法
        SystemResponseUtils.update(map, systemMapper);
    }

    @Override
    public Map<String, Object> findByWxWord()
    {
        //使用工具类，展示页面
        return SystemResponseUtils.view(systemMapper, "%wxController%");
    }

    @Override
    public void updateWx(Map<String, Object> map)
    {
        //调用工具类的更新方法
        SystemResponseUtils.update(map, systemMapper);
    }
}
