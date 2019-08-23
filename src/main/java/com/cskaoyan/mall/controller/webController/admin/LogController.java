package com.cskaoyan.mall.controller.webController.admin;

import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.bean.page.Page;
import com.cskaoyan.mall.service.admin.LogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/log")
public class LogController {

    @Autowired
    LogService logService;

    @RequestMapping("list")
    public Map<String, Object> listLog(Page page, String name) {
        PageHelper.startPage(page.getPage(), page.getLimit());
        List<Log> list;
        String sort = page.getSort() + " " + page.getOrder();
        if (name == null) {
            list = logService.listLogsBySort(sort);
        } else {
            list = logService.listLogsByLikeName(name, sort);
        }
        return returnList(list);
    }
    private Map<String, Object> returnList(List<Log> logList) {
        PageInfo<Log> pageInfo = new PageInfo<>(logList);
        long total = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", logList);
        Map<String, Object> result = new HashMap<>();
        result.put("data", map);
        result.put("errmsg", "成功");
        result.put("errno", 0);
        return result;
    }
}
