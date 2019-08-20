package com.cskaoyan.mall.utils.popularize;

import com.cskaoyan.mall.bean.Storage;
import org.springframework.web.multipart.MultipartFile;
import com.cskaoyan.mall.bean.base.BaseResponseModel;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

public class PopularizeUtils {

    /*文件(图片)上传处理*/
    public static Storage fileUpload(MultipartFile[] file) {
        Storage storage = new Storage();
        File upload = new File("G://temp/file/picture/", file[0].getOriginalFilename());
        String filename = file[0].getOriginalFilename();
        String s = UUID.randomUUID().toString();
        String substring = filename.substring(filename.lastIndexOf("."));
        String key = s + substring;
        String urlPrefix = "http://localhost:80/admin/storage/static/pic/";
        String url = urlPrefix + key;

        storage.setType(file[0].getContentType());
        int size = (int) file[0].getSize();
        storage.setSize(size);
        storage.setName(filename);
        storage.setAddTime(new Date());
        storage.setUpdateTime(new Date());
        storage.setKey(key);
        storage.setUrl(url);
        storage.setDeleted(false);
        try {
            filename = key;
            file[0].transferTo(upload);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storage;
    }

    /* CRUD 返回值 */
    public static <T> BaseResponseModel<T> respValue(T bean, int value) {
        BaseResponseModel<T> baseResponseModel = new BaseResponseModel<>();
        if (value == 1) {
            baseResponseModel.setData(bean);
            baseResponseModel.setErrmsg("成功");
            baseResponseModel.setErrno(0);
        } else {
            baseResponseModel.setData(null);
            baseResponseModel.setErrmsg("失败");
            baseResponseModel.setErrno(1);
        }
        return baseResponseModel;
    }

    /*判断 优惠券查找参数 type 和 status 是否为 "" 或者为null*//*
    public static boolean isEmptyOrNullOfType(String type) {
        if (type != null && !"".equals(type)) {
            return true;
        }
        return false;
    }

    public static boolean isEmptyOrNullOfStatus(String status) {
        if (status != null && !"".equals(status)) {
            return true;
        }
        return false;
    }*/
}
