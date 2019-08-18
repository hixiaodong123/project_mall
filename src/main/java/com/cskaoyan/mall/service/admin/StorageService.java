package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Storage;

import java.util.List;
import java.util.Map;

public interface StorageService {

    boolean insertStorage(Storage storage);

    boolean deleteStorageById(int id);

    boolean deleteStorageByIds(int[] ids);

    boolean updateStorage(Storage storage);

    List<Map> listStorages();

    List<Map> listStoragesBySort(String orderBy);

    List<Map> listStoragesByLikeName(String name, String orderBy);

    List<Map> listStoragesByLikeKey(String key, String orderBy);

    List<Map> listStoragesByNameAndKey(String name, String key, String orderBy);

    Storage listStorageByKey(String key);

    Storage listStoragesById(int id);


}
