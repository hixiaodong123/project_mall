package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.bean.StorageExample;
import com.cskaoyan.mall.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    StorageMapper storageMapper;

    @Override
    public boolean insertStorage(Storage storage) {
        return 0 != storageMapper.insert(storage);
    }

    @Override
    public boolean deleteStorageById(int id) {
        return false;
    }

    @Override
    public boolean deleteStorageByIds(int[] ids) {
        return false;
    }

    @Override
    public boolean updateStorage(Storage storage) {
        return 0 != storageMapper.updateByPrimaryKey(storage);
    }

    @Override
    public List<Map> listStorages() {
        return null;
    }



    @Override
    public List<Map> listStoragesBySort(String orderBy) {
        StorageExample storageExample = new StorageExample();
        storageExample.setOrderByClause(orderBy);
        storageExample.createCriteria().andDeletedEqualTo(false);
        List<Storage> storages = storageMapper.selectByExample(storageExample);
        return returnMap(storages);
    }

    private List<Map> returnMap(List<Storage> storages) {
        List<Map> list = new ArrayList<>();
        for (Storage storage : storages) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("addTime", date2String(storage.getAddTime()));
            map.put("deleted", storage.getDeleted());
            map.put("id", storage.getId());
            map.put("key", storage.getKey());
            map.put("name", storage.getName());
            map.put("size", storage.getSize());
            map.put("type", storage.getType());
            map.put("updateTime", date2String(storage.getUpdateTime()));
            map.put("url", storage.getUrl());
            list.add(map);
        }
        return list;
    }

    @Override
    public Storage listStoragesById(int id) {
        return storageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Map> listStoragesByLikeName(String name, String orderBy) {
        if ("".equals(name)) return listStoragesBySort(orderBy);
        StorageExample storageExample = new StorageExample();
        name = "%" + name + "%";
        storageExample.createCriteria().andNameLike(name);
        storageExample.setOrderByClause(orderBy);
        List<Storage> storages = storageMapper.selectByExample(storageExample);
        return returnMap(storages);
    }

    @Override
    public List<Map> listStoragesByLikeKey(String key, String orderBy) {
        if ("".equals(key)) return listStoragesBySort(orderBy);
        StorageExample storageExample = new StorageExample();
        key = "%" + key + "%";
        storageExample.createCriteria().andNameLike(key);
        storageExample.setOrderByClause(orderBy);
        List<Storage> storages = storageMapper.selectByExample(storageExample);
        return returnMap(storages);
    }

    @Override
    public List<Map> listStoragesByNameAndKey(String key, String name, String orderBy) {
        StorageExample storageExample = new StorageExample();
        key = "%" + key + "%";
        name = "%" + name + "%";
        storageExample.createCriteria().andNameLike(name).andNameLike(key);
        storageExample.setOrderByClause(orderBy);
        List<Storage> storages = storageMapper.selectByExample(storageExample);
        return returnMap(storages);
    }

    @Override
    public Storage listStorageByKey(String key) {
        StorageExample storageExample = new StorageExample();
        storageExample.createCriteria().andKeyEqualTo(key);
        List<Storage> storages = storageMapper.selectByExample(storageExample);
        Storage storage = storages.get(0);
        return storage;
    }

    //    将数据库中的日期转化为指定格式的字符串
    private String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
