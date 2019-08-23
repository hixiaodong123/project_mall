package com.cskaoyan.mall.controller.wx.upload;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.oss.MyOssClient;
import com.cskaoyan.mall.service.admin.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("wx/storage")
public class WxUpload {
    @Autowired
    StorageService storageService;
    @Autowired
    MyOssClient myOssClient;

    @RequestMapping("upload")
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

    private Map<String, Object> returnSuccess(Object data) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("errmsg", "成功");
        returnMap.put("errno", 0);
        returnMap.put("data", data);
        return returnMap;
    }

    //    将数据库中的日期转化为指定格式的字符串
    private String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
