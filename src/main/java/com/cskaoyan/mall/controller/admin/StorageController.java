package com.cskaoyan.mall.controller.admin;


import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.bean.page.Page;
import com.cskaoyan.mall.oss.MyOssClient;
import com.cskaoyan.mall.service.admin.StorageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("admin/storage")
public class StorageController {

    @Autowired
    StorageService storageService;

    @Autowired
    MyOssClient myOssClient;

    @RequestMapping("list")
    public Map<String, Object> listStorage(Page page,String key, String name) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(page.getPage(), page.getLimit());
        List<Map> list = new ArrayList<>();
        String orderBy = page.getSort() + " " + page.getOrder();
        if (key == null) {
            if (name == null) {
                list = storageService.listStoragesBySort(orderBy);
            } else {
                list = storageService.listStoragesByLikeName(name, orderBy);
            }
        }
        else {
            if (name == null) {
                list = storageService.listStoragesByLikeKey(key, orderBy);
            }
            else {

                list = storageService.listStoragesByNameAndKey(name, key, orderBy);
            }
        }
        Map data = returnList(list);
        return returnSuccess(data);
    }

    private Map<String, Object> returnSuccess(Object data) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("errmsg", "成功");
        returnMap.put("errno", 0);
        returnMap.put("data", data);
        return returnMap;
    }


    private Map returnList(List<Map> list) {
        PageInfo<Map> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", list);
        return map;
    }

    @RequestMapping("create")
    public Map<String, Object> createStorage(MultipartFile file) throws ParseException, IOException {
//        Map<String, Object> map = myOssClient.localUpload(file);
        String url = myOssClient.ossFileUpload(file);
        Storage storage = new Storage();
        String key = url.substring(url.lastIndexOf("/") + 1);
        storage.setKey(key);
        storage.setUrl(url);
        storage.setSize(file.getSize());
        storage.setType(file.getContentType());
        storage.setName(file.getOriginalFilename());
        Date date = new Date();
        storage.setAddTime(date);
        storage.setUpdateTime(date);
        boolean b = storageService.insertStorage(storage);
        if (!b) return null;
        Storage storageByKey = storageService.listStorageByKey(key);
        Map<String, Object> map = new HashMap<>();
        map.put("url",  url);
        map.put("key", key);
        map.put("id", storageByKey.getId());
        map.put("size", storageByKey.getSize());
        map.put("type", storageByKey.getType());
        map.put("name", storageByKey.getName());
        map.put("addTime", date2String(storageByKey.getAddTime()));
        map.put("updateTime", date2String(storageByKey.getUpdateTime()));
        return returnSuccess(map);
    }

    @RequestMapping("update")
    public Map<String, Object> updateStorage(@RequestBody Storage storage) {
        storage.setUpdateTime(new Date());
        storageService.updateStorage(storage);
        return returnSuccess(storage);
    }

    @RequestMapping("delete")
    public Map<String, Object> deleteStorage(@RequestBody Storage storage) {
        Storage findStorage = storageService.listStoragesById(storage.getId());
        findStorage.setUpdateTime(new Date());
        findStorage.setDeleted(true);
        boolean flag = storageService.updateStorage(findStorage);
        if (flag) {
            Map<String, Object> map = new HashMap<>();
            map.put("errmsg", "成功");
            map.put("errno", 0);
            return map;
        }
        return null;
    }

    //    将数据库中的日期转化为指定格式的字符串
    private String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
