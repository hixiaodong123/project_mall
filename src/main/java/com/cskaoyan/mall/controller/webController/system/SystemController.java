package com.cskaoyan.mall.controller.webcontroller.statistics;

import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.system.SystemService;
import com.cskaoyan.mall.utils.SuccessResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description: 配置管理模块控制器
 * @author: Lime
 * @create: 2019-08-16 15:50
 **/

@RestController
@RequestMapping("/admin")
public class SystemController
{

    private final SystemService systemService;

    @Autowired
    public SystemController(SystemService systemService)
    {
        this.systemService = systemService;
    }

    @RequestMapping(value = "/config/mall", method = RequestMethod.GET)
    public BaseResponseModel mall()
    {
        //返回值的类型是一个BaseResponseModel,同时data里面装的是一组键值对,也就是map
        Map<String, Object> data = systemService.findByMallWord();
        return new SuccessResponseUtils<Map<String, Object>>().responseSuccess(data);
    }

    @RequestMapping(value = "/config/mall", method = RequestMethod.POST)
    public BaseResponseModel mallUpdate(@RequestBody Map<String, Object> map)
    {
        //接收到前端返回的数据,并封装成一个Map
        //将这个map传给业务层处理
        systemService.updateMall(map);
        //返回成功,data为空
        return new SuccessResponseUtils<>().responseSuccess(null);
    }

    @RequestMapping(value = "/config/express", method = RequestMethod.GET)
    public BaseResponseModel express()
    {
        //返回值的类型是一个BaseResponseModel,同时data里面装的是一组键值对,也就是map
        Map<String, Object> data = systemService.findByExpressWord();
        return new SuccessResponseUtils<Map<String, Object>>().responseSuccess(data);
    }

    @RequestMapping(value = "/config/express", method = RequestMethod.POST)
    public BaseResponseModel expressUpdate(@RequestBody Map<String, Object> map)
    {
        //接收到前端返回的数据,并封装成一个Map
        //将这个map传给业务层处理
        systemService.updateExpress(map);
        //返回成功,data为空
        return new SuccessResponseUtils<>().responseSuccess(null);
    }

    @RequestMapping(value = "/config/order", method = RequestMethod.GET)
    public BaseResponseModel order()
    {
        //返回值的类型是一个BaseResponseModel,同时data里面装的是一组键值对,也就是map
        Map<String, Object> data = systemService.findByOrderWord();
        return new SuccessResponseUtils<Map<String, Object>>().responseSuccess(data);
    }

    @RequestMapping(value = "/config/order", method = RequestMethod.POST)
    public BaseResponseModel orderUpdate(@RequestBody Map<String, Object> map)
    {
        //接收到前端返回的数据,并封装成一个Map
        //将这个map传给业务层处理
        systemService.updateOrder(map);
        //返回成功,data为空
        return new SuccessResponseUtils<>().responseSuccess(null);
    }

    @RequestMapping(value = "/config/wx", method = RequestMethod.GET)
    public BaseResponseModel wx()
    {
        //返回值的类型是一个BaseResponseModel,同时data里面装的是一组键值对,也就是map
        Map<String, Object> data = systemService.findByWxWord();
        return new SuccessResponseUtils<Map<String, Object>>().responseSuccess(data);
    }

    @RequestMapping(value = "/config/wx", method = RequestMethod.POST)
    public BaseResponseModel wxUpdate(@RequestBody Map<String, Object> map)
    {
        //接收到前端返回的数据,并封装成一个Map
        //将这个map传给业务层处理
        systemService.updateWx(map);
        //返回成功,data为空
        return new SuccessResponseUtils<>().responseSuccess(null);
    }

}