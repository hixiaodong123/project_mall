package com.cskaoyan.mall.utils.system;

import com.cskaoyan.mall.bean.System;
import com.cskaoyan.mall.bean.SystemExample;
import com.cskaoyan.mall.mapper.SystemMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 返回配置模块的Map类型的data
 * @author: Lime
 * @create: 2019-08-16 19:25
 **/

public class SystemResponseUtils
{
    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author Lime
     * @Description 传入的是list, 转换成data
     * @Date 20:13 2019/8/16
     * @Param [list]
     **/
    public static Map<String, Object> listToDataMap(List<System> list)
    {

        Map<String, Object> map = new HashMap<>();
        for (System system : list)
        {
            String key = system.getKeyName()/*.replaceAll("cskaoyan_mall", "litemall")*/;
            map.put(key, system.getKeyValue());
        }
        return map;

    }


    /**
     * @Author Lime
     * @Description 页面展示的方法
     * @Date 21:35 2019/8/16
     * @Param [systemMapper, keyWord] 
     * @return java.util.Map<java.lang.String,java.lang.Object> 
     **/
    public static Map<String,Object> view(SystemMapper systemMapper,String keyWord)
    {
        //使用example查询出所有key为商城配置的
        SystemExample systemExample = new SystemExample();
        systemExample.createCriteria().andKeyNameLike(keyWord);
        List<System> list = systemMapper.selectByExample(systemExample);
        Map<String, Object> map = new HashMap<>();
        for (System system : list)
        {
            String key = system.getKeyName();
            map.put(key, system.getKeyValue());
        }
        return map;
    }


    /**
     * @Author Lime
     * @Description 更新的方法
     * @Date 21:17 2019/8/16
     * @Param [map, systemMapper] 
     * @return void 
     **/
    public static void update(Map<String, Object> map, SystemMapper systemMapper)
    {
        List<System> list = new ArrayList<>();
        //传入的参数是前端数据,格式为键值对
        for (Map.Entry<String, Object> entry : map.entrySet())
        {
            System system = new System();
            system.setKeyName(entry.getKey());
            system.setKeyValue((String) entry.getValue());
            list.add(system);
        }
        for (System system : list)
        {
            SystemExample systemExample = new SystemExample();
            systemExample.createCriteria().andKeyNameEqualTo(system.getKeyName());
            //遍历更新每条数据
            systemMapper.updateByExampleSelective(system, systemExample);
        }
    }



    /**
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @Author Lime
     * @Description 传入的是Map, 转换成可以用于更新数据库的实体类System的list集合
     * @Date 20:13 2019/8/16
     * @Param [list]
     **/
    public static List<System> dataMapToSystemObj(Map<String, Object> map)
    {
        List<System> list = new ArrayList<>();
        //传入的参数是前端数据,格式为键值对
        for (Map.Entry<String, Object> entry : map.entrySet())
        {
            System system = new System();
            system.setKeyName(entry.getKey());
            system.setKeyValue((String) entry.getValue());
            list.add(system);
        }
        return list;
    }

}
