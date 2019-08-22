package com.cskaoyan.mall.controller.webcontroller.popularize;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.popularize.AdStorageService;
import com.cskaoyan.mall.utils.popularize.PopularizeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("admin/storage")
public class AdStorageController {

    @Autowired
    AdStorageService adStorageService;

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public BaseResponseModel<Storage> create(MultipartFile[] file) {
        BaseResponseModel<Storage> storageBaseResponseModel = new BaseResponseModel<>();
        Storage storage = PopularizeUtils.fileUpload(file);
        int insert = adStorageService.insert(storage);
        // Storage storage1 = null;
        if (insert == 1) {
            // storage1 = adStorageService.selectByKey(storage.getKey());
            storageBaseResponseModel.setData(storage);
            storageBaseResponseModel.setErrmsg("成功");
            storageBaseResponseModel.setErrno(0);
            return storageBaseResponseModel;
        } else {
            storageBaseResponseModel.setData(null);
            storageBaseResponseModel.setErrmsg("失败");
            storageBaseResponseModel.setErrno(1);
            return storageBaseResponseModel;
        }
    }

}
